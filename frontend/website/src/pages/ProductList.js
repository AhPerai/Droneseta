import styled from "styled-components"
import { Navbar } from "../components/Navbar"
import { Anuncio } from "../components/Anuncio"
import { Products } from "../components/Products"
import { Footer } from "../components/Footer"
import { useLocation } from "react-router";
import { useState } from "react";

const Container = styled.div`
    background-color: #fcf5f5;
`

const Title = styled.h1`
    margin: 20px;
`
const FilterContainer = styled.div`
    display: flex;
    justify-content: space-between;
`
const Filter = styled.div`
    margin: 20px;
`

const FilterText = styled.span`
    font-size: 20px;
    font-weight: 600;
    margin-right: 20px;
`

const Select = styled.select`
    font-size: 14px;
    font-weight: 600;
    padding: 10px;
    margin-right:20px;
    border: 2px solid black;
    border-radius: 2px;
`
const Option = styled.option`

`

export const ProductList = () => {
    const location = useLocation();
    let category = location.pathname.split("/")[2]
    let fetchCategory = category.substring(0, category.length -1).toUpperCase();
    let route = `http://localhost:8080/produto/categoria/${fetchCategory}`

    const [filter, setFilter] = useState({})
    const [sort, setSort] = useState("mais recentes")

    const handleFilter = (e) =>{
        const value = e.target.value;
        setFilter({
            [e.target.name]: value
        })
    }

    return (
        <Container>
            <Anuncio />
            <Navbar />
            <Title>CAMISAS & BLUSAS</Title>
            <FilterContainer>
                <Filter>
                    <FilterText>Filtrar por:</FilterText>
                    <Select name="tamanho" onChange={handleFilter} defaultValue="Tamanho">
                        <Option value="tamanho">Tamanho</Option>
                        <Option>PP</Option>
                        <Option>P</Option>
                        <Option>M</Option>
                        <Option>G</Option>
                        <Option>GG</Option>
                    </Select>
                </Filter>
                <Filter>
                    <FilterText>Ordenar por:</FilterText>
                    <Select onChange={(e) => setSort(e.target.value)}>
                        <Option value="mais recente">Mais recentes</Option>
                        <Option value="mais barato" >Mais Barato</Option>
                        <Option value="mais caro">Mais Caro</Option>
                    </Select>
                </Filter>
            </FilterContainer>
            <Products fetch={route} filter={filter} sort={sort}/>
            <Footer />
        </Container>
    )
}
