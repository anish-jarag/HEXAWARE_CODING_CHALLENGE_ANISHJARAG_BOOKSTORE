import { Link } from "react-router-dom";

const Home = () => {
  const backgroundImage =
    "https://www.shutterstock.com/image-photo/adult-learning-education-concept-woman-600nw-2493354727.jpg";

  return (
    <div
      style={{
        backgroundImage: `linear-gradient(rgba(0,0,0,0.65), rgba(0,0,0,0.65)), url(${backgroundImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
        width: "100vw",
        color: "white",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        textAlign: "center",
      }}
    >
      <div style={{ maxWidth: "700px", padding: "20px" }}>
        <h1 className="display-3 fw-bold mb-4">
          Welcome to <span className="text-primary">HexaBook</span>
        </h1>
        <p className="lead mb-5">
          Your ultimate digital library system to manage and access books
          securely.
        </p>
        <div>
          <Link to="/login" className="btn btn-primary btn-lg me-3 px-5">
            Login
          </Link>
          <Link to="/register" className="btn btn-outline-light btn-lg px-5">
            Register
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
