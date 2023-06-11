import { useState } from "react"
import { useNavigate } from "react-router-dom"
import styled from "styled-components"
import { Navbar } from "../components/Navbar"
import { signUpData } from "../data"
import { BASE_URL } from "../utils/baseURL"
import axios from "axios";

const Container = styled.div`
    background-color: #fcf5f5;
    height: 100vh;
`

const CadastroContainer = styled.div`
    height: 80%;
    width: 100vw;
    display: flex; 
    align-items: center;
    justify-content: center;
`
const Wrapper = styled.div`     
    width: 40%;
    background-color: white;
    padding: 20px;
    text-align: center;
    overflow-x: hidden;
`
const Title = styled.h1`
    font-size: 24px;
    font-weigth: 600;
`

const ProgressBar = styled.div`
    width: 100%;
    margin: 40px auto 20px auto;
`

const SignUpSteps = styled.ol`
    display: flex;
    width: 60%;
    justify-content: space-between;
    margin: auto;
    padding: 0;
    list-style-type: none;
    position: relative;
    &:before {
        content: '';
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
        width: 100%;
        height: 1.5px;
        background-color: #555;
    }
`

const SignUpItem = styled.li`
    width: 25px;
    height: 25px;
    background-color: #fff;
    border-radius: 50%;
    border: 2px solid black;
    display: grid;
    place-items: center;
    font-size: 0.8rem;
    cursor: pointer;
    z-index: 1;
    &:before {
        width: 100px;
        content: ${props => `'${props.data_title}'`};
        position: absolute;
        font-size: 0.9rem;
        top: -1.5rem;
        color: black; 
    }
    ${props => props.active &&
        `
          background-color: teal;
          border-color: teal;
          color: white;
          &:before{ color: teal}
        `}
`

const FormWrapper = styled.div`
    width: 300%;
    display: flex;
    transition: all 0.3s linear;
    transform: translateX(${props => props.index / -3 * 100}%);
`

const Form = styled.form`
    display: flex;
    flex-wrap: wrap;
    align-content: space-between;
    width: 100%;
    padding: 20px;
    
`
const Input = styled.input`
    flex:1;
    margin: 20px 10px 0px 0px;
    min-width: ${props => props.mw}%;
    padding: 10px;  
    font-size: 12px;
    max-height: 15px;
    border: 2px solid #b1b1b1;
    -webkit-appearance: none; 
    -moz-appearance: none; 
`
const Termos = styled.span`
    font-size: 12px;
    margin: 20px 0px;
`
const ButtonContainer = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    margin-top: 20px;
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
    max-height: 50px;
`
const Error = styled.span`
  color: red;
  font-size: 14px;
`;

const Success = styled.span`
  color: green;
  font-size: 14px;
`;

export const Cadastro = () => {
    //State & Handlers - Index do Cadastro
    const [currentIndex, serCurrentIndex] = useState(0)

    function nextIndex(n) {
        if (currentIndex + n > -1 && currentIndex + n < 3) {
            serCurrentIndex(prev => prev + n)
        }
    }

    function handleNext(e) {
        e.preventDefault();
        nextIndex(1)
    }

    const [clienteData, setClienteData] = useState({
        nome: "",
        sobrenome: "",
        email: "",
        senha: "",
        confirmarSenha: "",
    })

    const [endereco, setEndereco] = useState({
        cep: "",
        pais: "Brasil",
        estado: "",
        cidade: "",
        bairro: "",
        rua: "",
        numero: ""
    })

    const [cartao, setCartao] = useState({
        numero: "",
        cvv: "",
        vencimento: ""
    })

    const [error, setError] = useState(false)
    const [success, setSuccess] = useState(false)

    const navigate = useNavigate();

    const onChangeCliente = (e) => {
        setClienteData({ ...clienteData, [e.target.name]: e.target.value })
    }

    const onChangeEndereco = (e) => {
        setEndereco({ ...endereco, [e.target.name]: e.target.value })
    }

    const onChangeCartao = (e) => {
        setCartao({ ...cartao, [e.target.name]: e.target.value })
    }

    const handleSubmit = async (e) => {
        e.preventDefault()

        const { nome, sobrenome, email, senha } = clienteData;
        const newCliente = ({
            nome: `${nome} ${sobrenome}`,
            email,
            senha,
            endereco: {...endereco, numero: parseInt(endereco.numero)},
            cartaoInfo: {
                ...cartao, vencimento: new Date(cartao.vencimento).toLocaleString('pt-BR', {
                    year: 'numeric', month: '2-digit', day: '2-digit'
                })
            }
        });

        try {
            await axios.post(`${BASE_URL}/signup/cliente`, newCliente)
            setSuccess(true)
            setTimeout(() => navigate("/"), 2000);
        } catch (error) {
            console.log(error);
            setError(true)
        }
    }

    return (
        <Container>
            <Navbar />
            <CadastroContainer>
                <Wrapper>
                    <Title>CADASTRO</Title>
                    <ProgressBar>
                        <SignUpSteps>
                            {signUpData.map((item, index) => (
                                <SignUpItem key={item} data_title={item} active={currentIndex >= index ? 1 : 0}>
                                    {index + 1}
                                </SignUpItem>
                            ))}
                        </SignUpSteps>
                    </ProgressBar>
                    <FormWrapper index={currentIndex}>
                        <Form onSubmit={handleNext}>
                            <Input name="nome" placeholder="Nome" mw="40" onChange={onChangeCliente}
                                pattern="^[a-zA-Z]{3,}$" required />
                            <Input name="sobrenome" placeholder="Sobrenome" mw="40" onChange={onChangeCliente}
                                pattern="^[a-zA-Z]{3,}$" required />
                            <Input name="email" placeholder="Email" mw="80" type="email" onChange={onChangeCliente}
                                required />
                            <Input name="senha" placeholder="Insira uma senha de até 3 caractéres" mw="40" type="password"
                                onChange={onChangeCliente} pattern="^(?=.*[a-zA-Z0-9])[a-zA-Z0-9]{3,}$" required />
                            <Input name="confirmarSenha" placeholder="Confirmar Senha" type="password" mw="40" onChange={onChangeCliente}
                                pattern={clienteData.senha} required />

                            <ButtonContainer>
                                <Button type="submit">PRÓXIMO</Button>
                            </ButtonContainer>
                        </Form>

                        <Form onSubmit={handleNext}>
                            <Input name="pais" placeholder="Pais" mw="40" onChange={onChangeEndereco}
                                pattern="^[A-Za-z]{3,}$" defaultValue="Brasil" required />
                            <Input name="estado" placeholder="Sigla Estadual" mw="40" onChange={onChangeEndereco}
                                pattern="^[A-Z]{2}$" required />
                            <Input name="cidade" placeholder="Cidade" mw="40" onChange={onChangeEndereco}
                                pattern="^.{3,}$" required />
                            <Input name="bairro" placeholder="Bairro" mw="40" onChange={onChangeEndereco}
                                pattern="^.{3,}$" required />
                            <Input name="rua" placeholder="Rua" mw="45" onChange={onChangeEndereco}
                                pattern="^.{3,}$" required />
                            <Input name="numero" placeholder="Numero" mw="15" onChange={onChangeEndereco}
                                pattern="^[0-9]{1,5}$" required />
                            <Input name="cep" placeholder="CEP" mw="15" onChange={onChangeEndereco}
                                pattern="^\d{8}$" required />

                            <ButtonContainer>
                                <Button type="button" onClick={() => nextIndex(-1)}>ANTERIOR</Button>
                                <Button type="submit">PRÓXIMO</Button>
                            </ButtonContainer>
                        </Form>

                        <Form onSubmit={handleSubmit}>
                            <Input name="numero" placeholder="Numero do Cartão (13-16 digitos)" mw="80" onChange={onChangeCartao}
                                pattern="^\d{13,16}$" required />
                            <Input name="cvv" placeholder="CVV" mw="25" onChange={onChangeCartao}
                                pattern="^\d{3}$" required />
                            <Input name="vencimento" type="date" placeholder="Data de Vencimento" mw="25" onChange={onChangeCartao} required />
                            <Termos>
                                Eu tenho ciência e concordo com a <b>Política de Privacidade</b>, permitindo transferência internacional dos meus dados pessoais, que se tornarão automaticamente visíveis para os serviços de <b>DRONESETA</b>
                            </Termos>
                            {success && <Success>Novo cadastro realizado com sucesso!</Success>}
                            {error && <Error>Algo deu errado...</Error>}
                            <ButtonContainer>
                                <Button type="button" onClick={() => nextIndex(-1)}>ANTERIOR</Button>
                                <Button type="submit" >CADASTRAR-SE</Button>
                            </ButtonContainer>
                        </Form>

                    </FormWrapper>
                </Wrapper>
            </CadastroContainer>
        </Container>
    )
}
