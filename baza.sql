--
-- PostgreSQL database dump
--

\restrict bp2CtUpHsloaCWPXoR1n7pDCLx17a9UNoKFLjlV2v1woJ3j7sxpdy9Ljt4gsJJv

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autorzy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autorzy (
    id bigint NOT NULL,
    imie character varying(100) NOT NULL,
    nazwisko character varying(100) NOT NULL,
    bio text
);


ALTER TABLE public.autorzy OWNER TO postgres;

--
-- Name: autorzy_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autorzy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autorzy_id_seq OWNER TO postgres;

--
-- Name: autorzy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.autorzy_id_seq OWNED BY public.autorzy.id;


--
-- Name: czlonkowie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.czlonkowie (
    id bigint NOT NULL,
    imie character varying(100) NOT NULL,
    nazwisko character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    telefon character varying(20),
    status character varying(20) DEFAULT 'AKTYWNY'::character varying NOT NULL,
    maks_wypozyczen integer DEFAULT 5 NOT NULL,
    data_dolaczenia timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.czlonkowie OWNER TO postgres;

--
-- Name: czlonkowie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.czlonkowie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.czlonkowie_id_seq OWNER TO postgres;

--
-- Name: czlonkowie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.czlonkowie_id_seq OWNED BY public.czlonkowie.id;


--
-- Name: egzemplarze; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.egzemplarze (
    id bigint NOT NULL,
    ksiazka_id bigint NOT NULL,
    status character varying(20) DEFAULT 'DOSTEPNY'::character varying NOT NULL,
    stan character varying(20) DEFAULT 'DOBRY'::character varying NOT NULL,
    kod_lokalizacji character varying(50)
);


ALTER TABLE public.egzemplarze OWNER TO postgres;

--
-- Name: egzemplarze_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.egzemplarze_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.egzemplarze_id_seq OWNER TO postgres;

--
-- Name: egzemplarze_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.egzemplarze_id_seq OWNED BY public.egzemplarze.id;


--
-- Name: kary; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kary (
    id bigint NOT NULL,
    wypozyczenie_id bigint NOT NULL,
    kwota numeric(10,2) NOT NULL,
    status character varying(20) DEFAULT 'NIEOPLACONA'::character varying NOT NULL,
    data_naliczenia timestamp without time zone DEFAULT now() NOT NULL,
    data_oplatenia timestamp without time zone
);


ALTER TABLE public.kary OWNER TO postgres;

--
-- Name: kary_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kary_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kary_id_seq OWNER TO postgres;

--
-- Name: kary_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kary_id_seq OWNED BY public.kary.id;


--
-- Name: kategorie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kategorie (
    id bigint NOT NULL,
    nazwa character varying(100) NOT NULL,
    nadkategoria_id bigint
);


ALTER TABLE public.kategorie OWNER TO postgres;

--
-- Name: kategorie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kategorie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kategorie_id_seq OWNER TO postgres;

--
-- Name: kategorie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kategorie_id_seq OWNED BY public.kategorie.id;


--
-- Name: ksiazki; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ksiazki (
    id bigint NOT NULL,
    isbn character varying(20) NOT NULL,
    tytul character varying(500) NOT NULL,
    wydawnictwo character varying(200),
    rok_wydania integer,
    jezyk character varying(10) DEFAULT 'pl'::character varying,
    opis text
);


ALTER TABLE public.ksiazki OWNER TO postgres;

--
-- Name: ksiazki_autorzy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ksiazki_autorzy (
    ksiazka_id bigint NOT NULL,
    autor_id bigint NOT NULL
);


ALTER TABLE public.ksiazki_autorzy OWNER TO postgres;

--
-- Name: ksiazki_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ksiazki_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ksiazki_id_seq OWNER TO postgres;

--
-- Name: ksiazki_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ksiazki_id_seq OWNED BY public.ksiazki.id;


--
-- Name: rezerwacje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rezerwacje (
    id bigint NOT NULL,
    czlonek_id bigint NOT NULL,
    ksiazka_id bigint NOT NULL,
    status character varying(20) DEFAULT 'OCZEKUJACA'::character varying NOT NULL,
    data_rezerwacji timestamp without time zone DEFAULT now() NOT NULL,
    wygasa_o timestamp without time zone
);


ALTER TABLE public.rezerwacje OWNER TO postgres;

--
-- Name: rezerwacje_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rezerwacje_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rezerwacje_id_seq OWNER TO postgres;

--
-- Name: rezerwacje_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rezerwacje_id_seq OWNED BY public.rezerwacje.id;


--
-- Name: wypozyczenia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wypozyczenia (
    id bigint NOT NULL,
    czlonek_id bigint NOT NULL,
    egzemplarz_id bigint NOT NULL,
    data_wypozyczenia date DEFAULT CURRENT_DATE NOT NULL,
    termin_zwrotu date NOT NULL,
    data_zwrotu date,
    status character varying(20) DEFAULT 'AKTYWNE'::character varying NOT NULL
);


ALTER TABLE public.wypozyczenia OWNER TO postgres;

--
-- Name: wypozyczenia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wypozyczenia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wypozyczenia_id_seq OWNER TO postgres;

--
-- Name: wypozyczenia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wypozyczenia_id_seq OWNED BY public.wypozyczenia.id;


--
-- Name: autorzy id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autorzy ALTER COLUMN id SET DEFAULT nextval('public.autorzy_id_seq'::regclass);


--
-- Name: czlonkowie id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.czlonkowie ALTER COLUMN id SET DEFAULT nextval('public.czlonkowie_id_seq'::regclass);


--
-- Name: egzemplarze id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.egzemplarze ALTER COLUMN id SET DEFAULT nextval('public.egzemplarze_id_seq'::regclass);


--
-- Name: kary id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kary ALTER COLUMN id SET DEFAULT nextval('public.kary_id_seq'::regclass);


--
-- Name: kategorie id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kategorie ALTER COLUMN id SET DEFAULT nextval('public.kategorie_id_seq'::regclass);


--
-- Name: ksiazki id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki ALTER COLUMN id SET DEFAULT nextval('public.ksiazki_id_seq'::regclass);


--
-- Name: rezerwacje id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezerwacje ALTER COLUMN id SET DEFAULT nextval('public.rezerwacje_id_seq'::regclass);


--
-- Name: wypozyczenia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wypozyczenia ALTER COLUMN id SET DEFAULT nextval('public.wypozyczenia_id_seq'::regclass);


--
-- Data for Name: autorzy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autorzy (id, imie, nazwisko, bio) FROM stdin;
\.


--
-- Data for Name: czlonkowie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.czlonkowie (id, imie, nazwisko, email, telefon, status, maks_wypozyczen, data_dolaczenia) FROM stdin;
2	Jan	Kowalski	jankowalski@kontak.pl	+48 998 112 999	AKTYWNY	5	2026-07-18 01:44:02.118404
4	Janina	Kowalska	janinakowalska@email.com	+48 721 654 123	AKTYWNY	5	2026-07-18 01:55:37.600055
5	Janina	Kowalska	janinakowalska1@email.com	+48 721 654 124	AKTYWNY	5	2026-07-18 01:58:10.789073
6	Elżbieta	Kowalska	ekowalska@email.com	+48 887 654 213	AKTYWNY	5	2026-07-18 01:59:56.207663
\.


--
-- Data for Name: egzemplarze; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.egzemplarze (id, ksiazka_id, status, stan, kod_lokalizacji) FROM stdin;
\.


--
-- Data for Name: kary; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kary (id, wypozyczenie_id, kwota, status, data_naliczenia, data_oplatenia) FROM stdin;
\.


--
-- Data for Name: kategorie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kategorie (id, nazwa, nadkategoria_id) FROM stdin;
\.


--
-- Data for Name: ksiazki; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ksiazki (id, isbn, tytul, wydawnictwo, rok_wydania, jezyk, opis) FROM stdin;
14	754-43-123-45-689	Quo Vadis	brak wydawnictwa	1895	pl	brak opisu
\.


--
-- Data for Name: ksiazki_autorzy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ksiazki_autorzy (ksiazka_id, autor_id) FROM stdin;
\.


--
-- Data for Name: rezerwacje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rezerwacje (id, czlonek_id, ksiazka_id, status, data_rezerwacji, wygasa_o) FROM stdin;
\.


--
-- Data for Name: wypozyczenia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wypozyczenia (id, czlonek_id, egzemplarz_id, data_wypozyczenia, termin_zwrotu, data_zwrotu, status) FROM stdin;
\.


--
-- Name: autorzy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autorzy_id_seq', 1, false);


--
-- Name: czlonkowie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.czlonkowie_id_seq', 7, true);


--
-- Name: egzemplarze_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.egzemplarze_id_seq', 1, false);


--
-- Name: kary_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kary_id_seq', 1, false);


--
-- Name: kategorie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kategorie_id_seq', 1, false);


--
-- Name: ksiazki_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ksiazki_id_seq', 14, true);


--
-- Name: rezerwacje_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rezerwacje_id_seq', 1, false);


--
-- Name: wypozyczenia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wypozyczenia_id_seq', 1, false);


--
-- Name: autorzy autorzy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autorzy
    ADD CONSTRAINT autorzy_pkey PRIMARY KEY (id);


--
-- Name: czlonkowie czlonkowie_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.czlonkowie
    ADD CONSTRAINT czlonkowie_email_key UNIQUE (email);


--
-- Name: czlonkowie czlonkowie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.czlonkowie
    ADD CONSTRAINT czlonkowie_pkey PRIMARY KEY (id);


--
-- Name: egzemplarze egzemplarze_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.egzemplarze
    ADD CONSTRAINT egzemplarze_pkey PRIMARY KEY (id);


--
-- Name: kary kary_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kary
    ADD CONSTRAINT kary_pkey PRIMARY KEY (id);


--
-- Name: kategorie kategorie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kategorie
    ADD CONSTRAINT kategorie_pkey PRIMARY KEY (id);


--
-- Name: ksiazki_autorzy ksiazki_autorzy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki_autorzy
    ADD CONSTRAINT ksiazki_autorzy_pkey PRIMARY KEY (ksiazka_id, autor_id);


--
-- Name: ksiazki ksiazki_isbn_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki
    ADD CONSTRAINT ksiazki_isbn_key UNIQUE (isbn);


--
-- Name: ksiazki ksiazki_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki
    ADD CONSTRAINT ksiazki_pkey PRIMARY KEY (id);


--
-- Name: rezerwacje rezerwacje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezerwacje
    ADD CONSTRAINT rezerwacje_pkey PRIMARY KEY (id);


--
-- Name: wypozyczenia wypozyczenia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wypozyczenia
    ADD CONSTRAINT wypozyczenia_pkey PRIMARY KEY (id);


--
-- Name: egzemplarze egzemplarze_ksiazka_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.egzemplarze
    ADD CONSTRAINT egzemplarze_ksiazka_id_fkey FOREIGN KEY (ksiazka_id) REFERENCES public.ksiazki(id);


--
-- Name: rezerwacje fk_rezerwacje_czlonkowie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezerwacje
    ADD CONSTRAINT fk_rezerwacje_czlonkowie FOREIGN KEY (id) REFERENCES public.czlonkowie(id);


--
-- Name: kary kary_wypozyczenie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kary
    ADD CONSTRAINT kary_wypozyczenie_id_fkey FOREIGN KEY (wypozyczenie_id) REFERENCES public.wypozyczenia(id);


--
-- Name: kategorie kategorie_nadkategoria_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kategorie
    ADD CONSTRAINT kategorie_nadkategoria_id_fkey FOREIGN KEY (nadkategoria_id) REFERENCES public.kategorie(id);


--
-- Name: ksiazki_autorzy ksiazki_autorzy_autor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki_autorzy
    ADD CONSTRAINT ksiazki_autorzy_autor_id_fkey FOREIGN KEY (autor_id) REFERENCES public.autorzy(id) ON DELETE CASCADE;


--
-- Name: ksiazki_autorzy ksiazki_autorzy_ksiazka_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ksiazki_autorzy
    ADD CONSTRAINT ksiazki_autorzy_ksiazka_id_fkey FOREIGN KEY (ksiazka_id) REFERENCES public.ksiazki(id) ON DELETE CASCADE;


--
-- Name: rezerwacje rezerwacje_czlonek_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezerwacje
    ADD CONSTRAINT rezerwacje_czlonek_id_fkey FOREIGN KEY (czlonek_id) REFERENCES public.czlonkowie(id);


--
-- Name: rezerwacje rezerwacje_ksiazka_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rezerwacje
    ADD CONSTRAINT rezerwacje_ksiazka_id_fkey FOREIGN KEY (ksiazka_id) REFERENCES public.ksiazki(id);


--
-- Name: wypozyczenia wypozyczenia_czlonek_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wypozyczenia
    ADD CONSTRAINT wypozyczenia_czlonek_id_fkey FOREIGN KEY (czlonek_id) REFERENCES public.czlonkowie(id);


--
-- Name: wypozyczenia wypozyczenia_egzemplarz_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wypozyczenia
    ADD CONSTRAINT wypozyczenia_egzemplarz_id_fkey FOREIGN KEY (egzemplarz_id) REFERENCES public.egzemplarze(id);


--
-- PostgreSQL database dump complete
--

\unrestrict bp2CtUpHsloaCWPXoR1n7pDCLx17a9UNoKFLjlV2v1woJ3j7sxpdy9Ljt4gsJJv

