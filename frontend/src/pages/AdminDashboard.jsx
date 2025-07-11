import { useEffect, useState } from "react";
import axios from "axios";

const AdminDashboard = () => {
  const [books, setBooks] = useState([]);
  const [form, setForm] = useState({
    isbn: "",
    title: "",
    author: "",
    publicationYear: "",
  });
  const [isEditing, setIsEditing] = useState(false);
  const token = localStorage.getItem("token");

  const fetchBooks = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/book/", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setBooks(res.data);
    } catch (err) {
      console.error("Failed to fetch books:", err);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditing) {
        await axios.put(`http://localhost:8080/api/book/${form.isbn}`, form, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setIsEditing(false);
      } else {
        await axios.post("http://localhost:8080/api/book/", form, {
          headers: { Authorization: `Bearer ${token}` },
        });
      }
      setForm({ isbn: "", title: "", author: "", publicationYear: "" });
      fetchBooks();
    } catch (err) {
      alert("Error saving book.");
      console.error(err);
    }
  };

  const handleEdit = (book) => {
    setForm(book);
    setIsEditing(true);
  };

  const handleDelete = async (isbn) => {
    if (window.confirm("Delete this book?")) {
      try {
        await axios.delete(`http://localhost:8080/api/book/${isbn}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        fetchBooks();
      } catch (err) {
        console.error("Delete error:", err);
      }
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="fw-bold text-primary">Admin Dashboard</h2>
      <form onSubmit={handleSubmit} className="row g-3 mt-3 mb-4">
        <div className="col-md-3">
          <input
            type="text"
            name="isbn"
            className="form-control"
            placeholder="ISBN"
            value={form.isbn}
            onChange={handleChange}
            required
            disabled={isEditing}
          />
        </div>
        <div className="col-md-3">
          <input
            type="text"
            name="title"
            className="form-control"
            placeholder="Title"
            value={form.title}
            onChange={handleChange}
            required
          />
        </div>
        <div className="col-md-3">
          <input
            type="text"
            name="author"
            className="form-control"
            placeholder="Author"
            value={form.author}
            onChange={handleChange}
            required
          />
        </div>
        <div className="col-md-2">
          <input
            type="number"
            name="publicationYear"
            className="form-control"
            placeholder="Year"
            value={form.publicationYear}
            onChange={handleChange}
            required
          />
        </div>
        <div className="col-md-1">
          <button type="submit" className="btn btn-primary w-100">
            {isEditing ? "Update" : "Add"}
          </button>
        </div>
      </form>

      <table className="table table-striped table-hover">
        <thead className="table-dark">
          <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Year</th>
            <th style={{ width: "120px" }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {books.map((b) => (
            <tr key={b.isbn}>
              <td>{b.isbn}</td>
              <td>{b.title}</td>
              <td>{b.author}</td>
              <td>{b.publicationYear}</td>
              <td>
                <button
                  className="btn btn-sm btn-warning me-2"
                  onClick={() => handleEdit(b)}
                >
                  Edit
                </button>
                <button
                  className="btn btn-sm btn-danger"
                  onClick={() => handleDelete(b.isbn)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminDashboard;
