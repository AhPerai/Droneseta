import styled from "styled-components";
import { Anuncio } from "../components/Anuncio";
import { Carousel } from "../components/Carousel";
import { Categories } from "../components/Categories";
import { Footer } from "../components/Footer";
import { Navbar } from "../components/Navbar";
import { Products } from "../components/Products";
import { SectionTitle } from "../components/SectionTitle";

const Container = styled.div`
    background-color: #fcf5f5;
`

const Home = () => {
    return (
        <Container>
            <Anuncio></Anuncio>
            <Navbar></Navbar>
            <Carousel></Carousel>
            <Categories></Categories>
            <SectionTitle title="CONHEÇA NOSSOS PRODUTOS"></SectionTitle>
            <Products fetch={'http://localhost:8080/produto/populares'} filter={''} sort={''}/>
            <Footer></Footer>
        </Container>
    )
}

export default Home;
