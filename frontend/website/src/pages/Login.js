import styled from "styled-components"
import { Navbar } from "../components/Navbar"
import { useDispatch, useSelector } from "react-redux"
import { login } from "../redux/loginRequests"
import { useState } from "react"

const Container = styled.div`
    background-color: #fcf5f5;
    height: 100vh;
`

const LoginContainer = styled.div`
    height: 80%;
    width: 100vw;
    display: flex; 
    align-items: center;
    justify-content: center;
`
const Wrapper = styled.div`     
    width: 25%;
    background-color: white;
    padding: 20px;
    text-align: center;
`
const Title = styled.h1`
    font-size: 24px;
    font-weigth: 600;
`

const Form = styled.form`
    display: flex;
    flex-direction: column;
`
const Input = styled.input`
    flex:1;
    margin: 10px 0px;
    min-width: 40%;
    padding: 10px;  
    font-size: 18px;
`
const Link = styled.a`
    margin: 5px 0px;
    font-size: 14px;
    text-decoration: underline;
    cursor: pointer;
`
const ButtonContainer = styled.div`
    display: flex;
    width: 100%;
    justify-content: center;
`

const Button = styled.button`
    width: 40%;
    border: none;
    padding: 15px 20px;
    background-color: teal;
    color: white;
    cursor: pointer;
    font-size: 14px;
    font-weight: 200;
    margin: 5px 0px;
    &:disabled {
        background-color: gray;
        cursor: not-allowed;
    }
`

const Error = styled.span`
  color: red;
  font-size: 14px;
`;

export const Login = () => {
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    const dispatch = useDispatch()
    const { fetchingData, error } =useSelector((state) => state.user)

    const handleClick = (e) => {
        e.preventDefault()
        login(dispatch, { email, senha })
    }
    return (
        <Container>
            <Navbar />
            <LoginContainer>
                <Wrapper>
                    <Title>LOGIN</Title>
                    <Form>
                        <Input placeholder="Email" onChange={(e) =>setEmail(e.target.value)}/>
                        <Input placeholder="Senha" type="password" onChange={(e) =>setSenha(e.target.value)}/>
                        <ButtonContainer>
                            <Button onClick = {handleClick} disabled={fetchingData}>ENTRAR</Button>
                        </ButtonContainer>
                        {error && <Error>O email ou senha informados estão incorretos...</Error>}
                        <Link>Esqueceu sua Senha?</Link>
                        <Link>Criar uma conta</Link>
                    </Form>
                </Wrapper>
            </LoginContainer>
        </Container>
    )
}
