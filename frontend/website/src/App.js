import { Cadastro } from "./pages/Cadastro";
import { Carrinho } from "./pages/Carrinho";
import Home from "./pages/Home";
import { Login } from "./pages/Login";
import { Product } from "./pages/Product";
import { ProductList } from "./pages/ProductList";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom"
import { useSelector } from "react-redux";

export const App = () => {
  const user = useSelector(state=>state.user.loggedUser);
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/produtos/:categoria" element={<ProductList />}></Route>
        <Route path="/produto/:id" element={<Product />}></Route>
        <Route path="/carrinho" element={<Carrinho />}></Route>
        <Route path="/login" element={user ? <Navigate to="/"/> : <Login />}></Route>
        <Route path="/cadastro" element={user ? <Navigate to="/"/> : <Cadastro />}></Route>
      </Routes>
    </Router>
  )
}

export default App;

