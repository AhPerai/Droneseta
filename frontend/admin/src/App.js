import Home from "./pages/home/Home.js";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom"
import Login from "./pages/login/Login.js";
import { useSelector } from "react-redux";

function App() {
  const stade = useSelector(state => state);
  console.log(stade)
  const admin = useSelector(state => state.user.loggedUser);
  return (
    <Router>
      <Routes>
        <Route path="/login" element={admin ? <Navigate to="/" /> : <Login />}></Route>
        {admin &&
          (<>
              <Route path="/" element={<Home />}></Route>
          </>)
        }
      </Routes>
    </Router>
  )
}

export default App;
