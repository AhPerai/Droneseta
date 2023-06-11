import styled from "styled-components"
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';
import { Link } from "react-router-dom";

const Info = styled.div`
    opacity: 0;
    height: 100%;
    width: 100%;
    position: absolute;
    background:rgba(0, 0, 0, 0.2);
    top: 0;
    left: 0;
    z-index: 2;
    display: flex;
    justify-content: center;
    align-items: center;
`

const Container = styled.div`
    flex: 1;
    margin: 5px;
    min-width: max(280px, 20%);
    height: 350px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 128, 128, 0.27);
    position: relative;
    transition: all 0.5s ease;
    &:hover ${Info}{
        opacity: 1;
    }
`

const Circle = styled.div`
    position: absolute;
    height: min(60%, 210px);
    width: min(60%, 210px);
    border-radius: 50%;
    background-color: white;
`
const Image = styled.img`
    height: 75%;
    z-index: 2;
`

const Icon = styled.div`
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: white;
    color: black !important;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px max(10px, 5%);
    transition: all 0.5s ease;
    cursor: pointer;
    &:hover {
        background-color: #00808080;
        color: white !important;
        transform: scale(1.2);
    }
`

export const Product = ({ item }) => {
    return (
        <Container>
            <Circle />

            <Image src={`data:image/png;base64,${item.imagem}`} />
            <Info>
                <Icon>
                    <ShoppingCartOutlinedIcon />
                </Icon>
                <Link to={`/produto/${item.id}`}>
                    <Icon>
                        <SearchOutlinedIcon />
                    </Icon>
                </Link>
                <Icon>
                    <FavoriteBorderOutlinedIcon />
                </Icon>
            </Info>
        </Container>
    )
}