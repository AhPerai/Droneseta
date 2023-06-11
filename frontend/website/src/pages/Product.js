import styled from "styled-components"
import { Anuncio } from "../components/Anuncio"
import { Footer } from "../components/Footer"
import { Navbar } from "../components/Navbar"
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import { BASE_URL } from '../utils/baseURL' 
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { addProduct } from "../redux/cartRedux";
import { useDispatch } from "react-redux"
 
const Container = styled.div`
    background-color: #fcf5f5;
`

const Wrapper = styled.div`
    padding: 50px 0px;
    display: flex;
`

const ImgContainer = styled.div`
    flex: 1;
    background-color: rgba(0, 128, 128, 0.15);
    position: relative;
    display: flex; 
    align-items: center;
    justify-content: center;
`

const Image = styled.img`
    width: 100%;
    height: 90vh;
    object-fit: cover;
    z-index: 2;
`

const InfoContainer = styled.div`
    flex: 1;
    background-color: white;
    padding: 0px 50px;
`

const Title = styled.h1`
    margin-top: 40px;
    font-weight: 200;
    font-size: 32px;
`

const Description = styled.p`
    font-size: 14px;
    margin: 20px 0px;
`

const Price = styled.span`
    font-size: 40px;
    font-weight: 200;
`

const Filter = styled.div`
    display: flex;
    margin: 40px 0px; 
    align-items: center;
`

const FilterTitle = styled.span`
    font-size: 20px;
    font: weight: 100;
    margin-right: 10px;
`
const FilterSize = styled.select`
    font-size: 14px;
    font-weight: 600;
    padding: 3px;
    margin-right:20px;
    border: 2px solid black;
    border-radius: 2px;
`

const AddCartContainer = styled.div`
    width: 50%;
    display: flex;
    align-items: center;
    justify-content: space-between;
`
const QtdContainer = styled.div`
    display: flex;
    align-items: center;
    font-weight: 700;
    font-size: 16px;
`

const Qtd = styled.span`
    width: 30px;
    height: 30px;
    border-radius: 10px;
    border: 2px solid teal;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px 5px;
`
const Button = styled.button`
    padding: 15px;
    font-weight: 600;
    font-size: 12px;
    border: 3px solid teal;
    background-color: white;
    cursor: pointer;
    transition: all .5s ease;
    &:hover{
        color: white;
        background-color: teal;
    }
`

export const Product = () => {
    const location = useLocation();
    const id = location.pathname.split("/")[2];

    const [product, setProduct] = useState({});
    const [quantity, setQuantity] = useState(1);
    const dispatch = useDispatch()

    useEffect(() => {
        const getProduto = async () => {
            try{
                const res = await axios.get(`${BASE_URL}/produto/${id}`)
                setProduct(res.data)
            }catch{}
        }
        getProduto()
    }, [id])

    const handleQuantity = (type) => {
        if(type === 'dec'){
            quantity > 1 ? setQuantity( quantity - 1 ) : setQuantity( quantity )
        }else{
            quantity < product.quantidade ? setQuantity( quantity + 1 ) : setQuantity( quantity )
        }
    }

    const addToCart = () =>{
        dispatch(
            addProduct({ ...product, qtdPedida: quantity })
        )
    }

    return (
        <Container>
            <Navbar />
            <Anuncio />
            <Wrapper>
                <ImgContainer>
                    <Image src={`data:image/png;base64,${product.imagem}`} />
                </ImgContainer>
                <InfoContainer>
                    <Title>{product.nome}</Title>
                    <Description>
                        {product.descricao}
                    </Description>
                    <Price>{product.preco && product.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</Price>
                    <Filter>
                        <FilterTitle>Tamanho</FilterTitle>
                        <FilterSize>
                            <option>{product.tamanho}</option>
                        </FilterSize>
                    </Filter>
                    <AddCartContainer>
                        <QtdContainer>
                            <RemoveIcon onClick={()=>handleQuantity('dec')}/>
                            <Qtd>{quantity}</Qtd>
                            <AddIcon onClick={()=>handleQuantity('acc')}/>
                        </QtdContainer>
                        <Button onClick={addToCart}>ADICIONAR AO CARRINHO</Button>
                    </AddCartContainer>
                </InfoContainer>

            </Wrapper>
            <Footer />
        </Container>
    )
}

