import { Link, useNavigate } from "react-router-dom";

const AdminNavbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    alert("Logged out successfully!");
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand fw-bold text-white" to="/admin">
          HexaBook Admin
        </Link>

        <div className="d-flex ms-auto">
          <Link className="nav-link text-white me-3" to="/admin">
            Dashboard
          </Link>
          <Link className="nav-link text-white me-3" to="/admin/books">
            Books
          </Link>
          <Link className="nav-link text-white me-3" to="/admin/users">
            Users
          </Link>
          <button
            onClick={handleLogout}
            className="btn btn-outline-light btn-sm"
          >
            Logout
          </button>
        </div>
      </div>
    </nav>
  );
};

export default AdminNavbar;
