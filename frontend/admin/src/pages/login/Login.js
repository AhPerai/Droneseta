import { useDispatch, useSelector } from "react-redux"
import { login } from "../../redux/loginRequest"
import { useState } from "react"
import Topbar from "../../componentes/topbar/Topbar"
import "./Login.css"

export default function Login() {
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    const dispatch = useDispatch()
    const { fetchingData, error } = useSelector((state) => state.user)

    const handleClick = (e) => {
        e.preventDefault()
        login(dispatch, { email, senha })
    }

    return (
        <div>
            <Topbar />
            <div class="loginWrapper">
                <h3 class="loginTitle">LOGIN - ADMINISTRAÇÃO</h3>
                <form class="loginForm">
                    <input class="loginInput" placeholder="Email" type="email" onChange={(e) => setEmail(e.target.value)} />
                    <input class="loginInput" placeholder="Senha" type="password" onChange={(e) => setSenha(e.target.value)} />
                    <button class="loginButton" onClick={handleClick} disabled={fetchingData}>ENTRAR</button>
                    {error && <span class="error">O email ou senha informados estão incorretos ou este usuário não tem acesso de administrador</span>}
                </form>
            </div>
        </div>
    )
}
