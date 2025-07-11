import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out successfully!");
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container-fluid">
        <Link className="navbar-brand fw-bold text-white" to="/">
          HexaBook
        </Link>

        <div className="d-flex ms-auto">
          <Link className="nav-link text-white me-3" to="/">
            Home
          </Link>

          {token ? (
            <>
              <Link className="nav-link text-white me-3" to="/books">
                Books
              </Link>
              <button
                onClick={handleLogout}
                className="btn btn-outline-light btn-sm"
              >
                Logout
              </button>
            </>
          ) : (
            <>
              <Link className="nav-link text-white me-3" to="/login">
                Login
              </Link>
              <Link className="nav-link text-white" to="/register">
                Register
              </Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
