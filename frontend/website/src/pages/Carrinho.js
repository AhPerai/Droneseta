import styled from 'styled-components';
import { Anuncio } from "../components/Anuncio"
import { Navbar } from "../components/Navbar"
import { Footer } from "../components/Footer"
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import { useDispatch, useSelector } from 'react-redux'
import { Link } from "react-router-dom";
import axios from "axios";
import { BASE_URL } from "../utils/baseURL"
import { useState, useEffect } from "react"
import { resetCart } from "../redux/cartRedux";

const Container = styled.div`
background-color: #fcf5f5;
`

const Wrapper = styled.div`
    padding: 20px;
    margin-top:40px;
    background-color: white;
`

const Title = styled.h1`
    font-weight: 600;
    text-align: center;
`

const Notification = styled.div`
    position: fixed;
    top: 100px;
    right: 20px;
    text-align: center;
    margin: 10px auto;
    background-color: ${props => props.pattern === 'success' ? 'rgb(232, 253, 245)' : 'rgba(252, 232, 232, 1)'};
    border-radius: 2px;
    border: 2px solid ${props => props.pattern === 'success' ? 'rgb(25, 169, 116)' : 'rgba(255, 159, 159, 1)'};
    color: ${props => props.pattern === 'success' ? 'rgb(25, 169, 116)' : 'rgba(181, 104, 104, 1)'} ;
    font-size: 14px;
    padding: 15px 5px;
    opacity: ${props => props.visible ? '1' : '0'};
`

const Warning = styled.div`
    text-align: center;
    margin: 10px auto;
    background-color: ${props => props.pattern === 'success' ? 'rgb(232, 253, 245)' : 'rgba(252, 232, 232, 1)'};
    border-radius: 2px;
    border: 2px solid ${props => props.pattern === 'success' ? 'rgb(25, 169, 116)' : 'rgba(255, 159, 159, 1)'};
    color: ${props => props.pattern === 'success' ? 'rgb(25, 169, 116)' : 'rgba(181, 104, 104, 1)'} ;
    font-size: 14px;
    padding: 15px 5px;
    width: 50vw;
`

const TopWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
`

const TopInfo = styled.div`
`
const TopLinks = styled.span`
    text-decoration: underline;
    cursor: pointer;
    margin: 0px 10px;
`

const TopButton = styled.button`
    padding: 10px;
    font-weight: 600;
    cursor: pointer;
    border: ${props => props.type === "filled" && "none"};
    background-color: ${props => props.type === "filled" ? "black" : "transparent"};
    color: ${props => props.type === "filled" && "white"} ;
    &:disabled{
        background-color: rgba(199, 199, 199, 1);
        cursor: not-allowed;
    }
`

const BottomWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`

const BottomInfo = styled.div`
    flex: 3;
`

const Hr = styled.hr`
    background-color: #eee;
    border: none;
    height: 1.25px; 
`

const Product = styled.div`
    display: flex;
    justify-content: space-between;
    padding-left: 20px;
`

const ProductDetails = styled.div`
    display: flex;
    flex: 2;
`

const Image = styled.img`
    width: 225px;
`

const Details = styled.div`
    padding: 20px;
    display:flex;
    flex-direction: column;
    justify-content: center;
`

const ProductSpecs = styled.span`
    font-size: 16px;
    margin-bottom: 20%;
`

const PriceDetails = styled.span`
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`
const ProductQtdContainer = styled.div`
    display: flex;
    align-items: center;
    margin-bottom: 25px;
`
const ProductQtd = styled.span`
    font-size: 20px;
    margin: 5px;
`
const ProductPrice = styled.span`
    font-size: 22px;
    font-weight: 200;
`

const Info = styled.div`
    flex: 1;
    border: 0.5px solid lightgray;
    border-radius: 10px;
    padding: 20px;
    height: fit-content;
    display: flex;
    flex-direction: column;
    align-items: center;
`

const InfoTitle = styled.h1`
    font-weight: 200;
    font-size: 24px;
`
const InfoItem = styled.div`
    margin: 30px 0px;
    display: flex;
    justify-content: space-between;
    width: 85%;
`
const InforItemText = styled.span`
    font-weight: 200;
    font-size: 16px;
`
const InforItemPrice = styled.span`
    font-weight: 600;
    font-size: 18px;
`

const Button = styled.button`
  width: 100%;
  padding: 10px;
  background-color: black;
  color: white;
  font-weight: 600;
  cursor: pointer;
  &:disabled{
      background-color: rgba(199, 199, 199, 1);
      cursor: not-allowed;
  }
`;

export const Carrinho = () => {
    const cart = useSelector(state => state.cart)
    const user = useSelector(state => state.user.loggedUser)
    const dispatch = useDispatch()
    const [success, setSuccess] = useState(false)
    const [error, setError] = useState(false)
    const [visible, setVisible] = useState(false)

    useEffect(() => {
        const timeout = setTimeout(() => setVisible(false), 4000);

        return () => {
            clearTimeout(timeout);
        };
    }, [visible]);

    const handleCompra = async (e) => {
        e.preventDefault();

        const modifiedProducts = cart.products.map((product) => {
            const { qtdPedida, ...rest } = product;
            return { ...rest, quantidade: qtdPedida };
        });

        const newOrder = {
            produtos: modifiedProducts,
            preco: cart.totalPrice,
            clienteId: user.id,
        }

        try {
            await axios.post(`${BASE_URL}/pedido`, newOrder)
            setSuccess(true)
        } catch (error) {
            setError(true)
        } finally {
            dispatch(resetCart())
            setVisible(true)
        }
    }

    return (
        <Container>
            <Anuncio />
            <Navbar />
            <Wrapper>
                <Title>MEU CARRINHO</Title>
                {!user && <Warning>Por favor, faça login na sua conta para efetuar a compra!</Warning>}
                {success &&
                    <Warning pattern="success" visible={visible}>
                        Seu pedido foi efetuado com sucesso, aguarde um momento que nosso drone o conduzira a sua casa assim que possível!
                </Warning>}
                {error &&
                    <Notification pattern="error" visible={visible}>
                        Lamentamos muito, mas não foi possível efetuar o pedido para estes produtos =(
                    </Notification>}
                <TopWrapper>
                    <Link to="/"><TopButton>VOLTAR A LOJA</TopButton></Link>
                    <TopInfo>
                        <TopLinks>{`Carrinho de Compras(${cart.quantity})`}</TopLinks>
                    </TopInfo>
                    <TopButton type="filled" disabled={user && cart.quantity !== 0 ? false : true}>FINALIZAR COMPRA</TopButton>
                </TopWrapper>
                <BottomWrapper>
                    <BottomInfo>
                        {cart.products.map(product => (
                            <div key={product.id}>
                                <Product>
                                    <ProductDetails>
                                        <Image src={`data:image/png;base64,${product.imagem}`} />
                                        <Details>
                                            <ProductSpecs><b>Produto:</b> {product.nome}</ProductSpecs>
                                            <ProductSpecs><b>Código:</b> {product.id}</ProductSpecs>
                                            <ProductSpecs><b>Tamanho:</b> {product.tamanho}</ProductSpecs>
                                        </Details>

                                    </ProductDetails>
                                    <PriceDetails>
                                        <ProductQtdContainer>
                                            <RemoveIcon />
                                            <ProductQtd>{product.qtdPedida}</ProductQtd>
                                            <AddIcon />
                                        </ProductQtdContainer>
                                        <ProductPrice>{(product.qtdPedida * product.preco).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</ProductPrice>
                                    </PriceDetails>
                                </Product>
                                <Hr />
                            </div>
                        ))}

                    </BottomInfo>
                    <Info>
                        <InfoTitle>INFORMAÇÕES DA COMPRA</InfoTitle>
                        <InfoItem>
                            <InforItemText>Valor Total: </InforItemText>
                            <InforItemPrice>{(cart.totalPrice).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</InforItemPrice>
                        </InfoItem>
                        <Button disabled={user && cart.quantity !== 0  ? false : true} onClick={handleCompra}>FINALIZAR COMPRA</Button>
                    </Info>
                </BottomWrapper>
            </Wrapper>
            <Footer />
        </Container>
    )
}
