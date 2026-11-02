const { useEffect, useMemo, useState } = React;

const api = {
  async get(path) {
    const response = await fetch(path, { headers: { Accept: 'application/json' } });
    if (!response.ok) {
      throw new Error('Błąd pobierania danych');
    }
    return response.json();
  },
  async post(path, payload) {
    const response = await fetch(path, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!response.ok) {
      const text = await response.text();
      throw new Error(text || 'Błąd zapisu');
    }
    return response.status === 204 ? null : response.json();
  },
  async delete(path) {
    const response = await fetch(path, { method: 'DELETE' });
    if (!response.ok) {
      throw new Error('Błąd usuwania');
    }
    return true;
  }
};

function App() {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [reservations, setReservations] = useState([]);
  const [dashboard, setDashboard] = useState({ totalBooks: 0, activeMembers: 0, totalReservations: 0, recentBooks: [], recentReservations: [] });
  const [search, setSearch] = useState('');
  const [bookForm, setBookForm] = useState({ title: '', isbn: '', publisher: '', yearOfRelease: '', language: 'pl', description: '' });
  const [memberForm, setMemberForm] = useState({ firstName: '', lastName: '', email: '', phoneNumber: '' });
  const [reservationForm, setReservationForm] = useState({ memberId: '', bookId: '' });
  const [error, setError] = useState('');

  const loadData = async () => {
    try {
      const [booksData, membersData, reservationsData, dashboardData] = await Promise.all([
        api.get('/api/books'),
        api.get('/api/members'),
        api.get('/api/reservations'),
        api.get('/api/dashboard')
      ]);
      setBooks(booksData);
      setMembers(membersData);
      setReservations(reservationsData);
      setDashboard(dashboardData);
      setError('');
    } catch (err) {
      setError(err.message || 'Brak połączenia z API');
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const filteredBooks = useMemo(() => {
    return books.filter((book) => {
      const query = search.toLowerCase();
      const authors = (book.authors || []).join(' ').toLowerCase();
      return !query || [book.title, book.isbn, book.publisher, authors].join(' ').toLowerCase().includes(query);
    });
  }, [books, search]);

  const submitBook = async (event) => {
    event.preventDefault();
    try {
      await api.post('/api/books', {
        title: bookForm.title,
        isbn: bookForm.isbn,
        publisher: bookForm.publisher,
        yearOfRelease: bookForm.yearOfRelease ? Number(bookForm.yearOfRelease) : null,
        language: bookForm.language,
        description: bookForm.description
      });
      setBookForm({ title: '', isbn: '', publisher: '', yearOfRelease: '', language: 'pl', description: '' });
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się dodać książki');
    }
  };

  const deleteBook = async (id) => {
    try {
      await api.delete(`/api/books/${id}`);
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się usunąć książki');
    }
  };

  const submitMember = async (event) => {
    event.preventDefault();
    try {
      await api.post('/api/members', {
        firstName: memberForm.firstName,
        lastName: memberForm.lastName,
        email: memberForm.email,
        phoneNumber: memberForm.phoneNumber
      });
      setMemberForm({ firstName: '', lastName: '', email: '', phoneNumber: '' });
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się dodać członka');
    }
  };

  const deleteMember = async (id) => {
    try {
      await api.delete(`/api/members/${id}`);
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się usunąć członka');
    }
  };

  const submitReservation = async (event) => {
    event.preventDefault();
    try {
      await api.post('/api/reservations', {
        memberId: Number(reservationForm.memberId),
        bookId: Number(reservationForm.bookId)
      });
      setReservationForm({ memberId: '', bookId: '' });
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się dodać rezerwacji');
    }
  };

  const deleteReservation = async (id) => {
    try {
      await api.delete(`/api/reservations/${id}`);
      await loadData();
    } catch (err) {
      setError(err.message || 'Nie udało się usunąć rezerwacji');
    }
  };

  return React.createElement(
    'div',
    { className: 'min-vh-100' },
    React.createElement(
      'nav',
      { className: 'navbar navbar-expand-lg navbar-dark shadow-sm', style: { background: '#0f172a', borderBottom: '1px solid rgba(148, 163, 184, 0.2)' } },
      React.createElement(
        'div',
        { className: 'container' },
        React.createElement('span', { className: 'navbar-brand mb-0 h1' }, 'LibraryMS'),
        React.createElement('div', { className: 'd-flex gap-3 text-light small' },
          React.createElement('span', null, 'Książki: ' + dashboard.totalBooks),
          React.createElement('span', null, 'Członkowie: ' + dashboard.activeMembers),
          React.createElement('span', null, 'Rezerwacje: ' + dashboard.totalReservations)
        )
      )
    ),
    React.createElement(
      'main',
      { className: 'container py-4' },
      error && React.createElement('div', { className: 'alert alert-danger' }, error),
      React.createElement(
        'div',
        { className: 'row g-4 mb-4' },
        [
          { label: 'Wszystkich książek', value: dashboard.totalBooks, variant: 'primary' },
          { label: 'Aktywnych członków', value: dashboard.activeMembers, variant: 'success' },
          { label: 'Wypożyczenia', value: dashboard.totalReservations, variant: 'warning' },
          { label: 'Rezerwacje', value: dashboard.totalReservations, variant: 'info' }
        ].map((stat) => React.createElement(
          'div',
          { className: 'col-md-3', key: stat.label },
          React.createElement(
            'div',
            { className: 'card border-0 shadow-sm h-100' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('div', { className: 'text-muted small' }, stat.label),
              React.createElement('div', { className: 'fs-2 fw-bold text-' + stat.variant }, stat.value)
            )
          )
        ))
      ),
      React.createElement(
        'div',
        { className: 'row g-4' },
        React.createElement(
          'div',
          { className: 'col-lg-8' },
          React.createElement(
            'div',
            { className: 'card shadow-sm border-0 h-100' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('h3', { className: 'mb-3' }, 'Książki'),
              React.createElement('div', { className: 'input-group mb-3' },
                React.createElement('span', { className: 'input-group-text' }, '🔎'),
                React.createElement('input', {
                  type: 'text',
                  className: 'form-control',
                  placeholder: 'Szukaj po tytule, autorze lub ISBN',
                  value: search,
                  onChange: (event) => setSearch(event.target.value)
                })
              ),
              React.createElement(
                'div',
                { className: 'table-responsive' },
                React.createElement(
                  'table',
                  { className: 'table table-hover align-middle' },
                  React.createElement(
                    'thead',
                    null,
                    React.createElement('tr', null,
                      React.createElement('th', null, 'Tytuł'),
                      React.createElement('th', null, 'Autor'),
                      React.createElement('th', null, 'ISBN'),
                      React.createElement('th', null, 'Akcja')
                    )
                  ),
                  React.createElement(
                    'tbody',
                    null,
                    filteredBooks.length === 0 ? React.createElement('tr', null, React.createElement('td', { colSpan: 4, className: 'text-center text-muted' }, 'Brak książek do wyświetlenia')) : filteredBooks.map((book) => React.createElement('tr', { key: book.id },
                      React.createElement('td', null, book.title),
                      React.createElement('td', null, (book.authors && book.authors.length ? book.authors.join(', ') : 'Brak autora')),
                      React.createElement('td', null, book.isbn || '—'),
                      React.createElement('td', null,
                        React.createElement('button', { className: 'btn btn-sm btn-outline-danger', onClick: () => deleteBook(book.id) }, 'Usuń')
                      )
                    ))
                  )
                )
              )
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'col-lg-4' },
          React.createElement(
            'div',
            { className: 'card shadow-sm border-0 mb-4' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('h4', { className: 'mb-3' }, 'Dodaj książkę'),
              React.createElement(
                'form',
                { onSubmit: submitBook },
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Tytuł', value: bookForm.title, onChange: (event) => setBookForm({ ...bookForm, title: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'ISBN', value: bookForm.isbn, onChange: (event) => setBookForm({ ...bookForm, isbn: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Wydawnictwo', value: bookForm.publisher, onChange: (event) => setBookForm({ ...bookForm, publisher: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { type: 'number', className: 'form-control', placeholder: 'Rok wydania', value: bookForm.yearOfRelease, onChange: (event) => setBookForm({ ...bookForm, yearOfRelease: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Język', value: bookForm.language, onChange: (event) => setBookForm({ ...bookForm, language: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('textarea', { className: 'form-control', rows: 3, placeholder: 'Opis', value: bookForm.description, onChange: (event) => setBookForm({ ...bookForm, description: event.target.value }) })
                ),
                React.createElement('button', { type: 'submit', className: 'btn btn-primary w-100' }, 'Dodaj książkę')
              )
            )
          ),
          React.createElement(
            'div',
            { className: 'card shadow-sm border-0' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('h4', { className: 'mb-3' }, 'Dodaj członka'),
              React.createElement(
                'form',
                { onSubmit: submitMember },
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Imię', value: memberForm.firstName, onChange: (event) => setMemberForm({ ...memberForm, firstName: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Nazwisko', value: memberForm.lastName, onChange: (event) => setMemberForm({ ...memberForm, lastName: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Email', type: 'email', value: memberForm.email, onChange: (event) => setMemberForm({ ...memberForm, email: event.target.value }) })
                ),
                React.createElement('div', { className: 'mb-3' },
                  React.createElement('input', { className: 'form-control', placeholder: 'Telefon', value: memberForm.phoneNumber, onChange: (event) => setMemberForm({ ...memberForm, phoneNumber: event.target.value }) })
                ),
                React.createElement('button', { type: 'submit', className: 'btn btn-success w-100' }, 'Dodaj członka')
              )
            )
          )
        )
      ),
      React.createElement(
        'div',
        { className: 'row g-4 mt-1' },
        React.createElement(
          'div',
          { className: 'col-lg-6' },
          React.createElement(
            'div',
            { className: 'card shadow-sm border-0 h-100' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('h3', { className: 'mb-3' }, 'Członkowie'),
              React.createElement(
                'div',
                { className: 'list-group' },
                members.length === 0 ? React.createElement('div', { className: 'text-muted' }, 'Brak członków.') : members.map((member) => React.createElement('div', { className: 'list-group-item d-flex justify-content-between align-items-center', key: member.id },
                  React.createElement('div', null,
                    React.createElement('div', { className: 'fw-semibold' }, member.firstName + ' ' + member.lastName),
                    React.createElement('div', { className: 'small text-muted' }, member.email)
                  ),
                  React.createElement('button', { className: 'btn btn-sm btn-outline-danger', onClick: () => deleteMember(member.id) }, 'Usuń')
                ))
              )
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'col-lg-6' },
          React.createElement(
            'div',
            { className: 'card shadow-sm border-0 h-100' },
            React.createElement(
              'div',
              { className: 'card-body' },
              React.createElement('h3', { className: 'mb-3' }, 'Rezerwacje'),
              React.createElement(
                'form',
                { className: 'row g-2 mb-3', onSubmit: submitReservation },
                React.createElement('div', { className: 'col-md-6' },
                  React.createElement('select', {
                    className: 'form-select',
                    value: reservationForm.memberId,
                    onChange: (event) => setReservationForm({ ...reservationForm, memberId: event.target.value }),
                    required: true
                  },
                    React.createElement('option', { value: '' }, 'Wybierz członka'),
                    ...members.map((member) => React.createElement('option', { key: member.id, value: member.id }, member.firstName + ' ' + member.lastName))
                  )
                ),
                React.createElement('div', { className: 'col-md-6' },
                  React.createElement('select', {
                    className: 'form-select',
                    value: reservationForm.bookId,
                    onChange: (event) => setReservationForm({ ...reservationForm, bookId: event.target.value }),
                    required: true
                  },
                    React.createElement('option', { value: '' }, 'Wybierz książkę'),
                    ...books.map((book) => React.createElement('option', { key: book.id, value: book.id }, book.title))
                  )
                ),
                React.createElement('div', { className: 'col-12' },
                  React.createElement('button', { type: 'submit', className: 'btn btn-warning w-100' }, 'Dodaj rezerwację')
                )
              ),
              React.createElement(
                'div',
                { className: 'list-group' },
                reservations.length === 0 ? React.createElement('div', { className: 'text-muted' }, 'Brak rezerwacji.') : reservations.map((reservation) => React.createElement('div', { className: 'list-group-item d-flex justify-content-between align-items-center', key: reservation.id },
                  React.createElement('div', null,
                    React.createElement('div', { className: 'fw-semibold' }, reservation.bookTitle || 'Brak tytułu'),
                    React.createElement('div', { className: 'small text-muted' }, reservation.memberName || 'Brak członka')
                  ),
                  React.createElement('button', { className: 'btn btn-sm btn-outline-danger', onClick: () => deleteReservation(reservation.id) }, 'Usuń')
                ))
              )
            )
          )
        )
      )
    )
  );
}

ReactDOM.createRoot(document.getElementById('root')).render(React.createElement(App));
