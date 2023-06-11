import styled from "styled-components"
import { Link } from "react-router-dom"
// import {tablet,mobile} from "../responsive"

const Container = styled.div`
    flex: 1;
    margin: 0px 5px;
    height: 70vh;
    position: relative;
`
const Image = styled.img`
    width: 100%; 
    height: 100%;
    object-fit: cover;
`
const Shadow = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%; 
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 2;
`

const Info = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    z-index: 3;
`

const Title = styled.h1`
    color: white;
    margin-bottom: 20px;
    font-size: 70px;
`
const Button = styled.button`
    border: 2px solid white;
    padding: 10px;
    background-color: transparent;
    color: white;
    font-size: 18px;
    font-weight: 600;
    cursor: pointer;
    transition: all .75s ease;
    &:hover {
        background-color: white;
        color: black;
    }
`


const CategoryItem = ({ item }) => {
    const routeCategory = item.title.toLowerCase();
    return (

        <Container>
            <Link to={`/produtos/${routeCategory}`}>
                <Shadow />
                <Image src={item.img} />
                <Info>
                    <Title>{item.title}</Title>
                    <Button>CONFIRA AGORA</Button>
                </Info>
            </Link>
        </Container>
    )
}
export default CategoryItem
