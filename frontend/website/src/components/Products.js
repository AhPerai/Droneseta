import styled from "styled-components"
import { Product } from "./Product"
import { useState, useEffect } from "react";
import axios from "axios";

const Container = styled.div`
    padding: 10px 15px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
`

export const Products = ({ fetch, filter, sort }) => {
    const [products, setProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);

    useEffect(() => {
        const getProducts = async () => {
            try {
                const result = await axios.get(fetch)
                setProducts(result.data)
            } catch (err) { }
        }
        getProducts()
    }, [fetch])

    useEffect(() => {
        setFilteredProducts(
            products.filter((item) =>
                Object.entries(filter).every(([key, value]) =>{
                    if(key !== value){
                        return item[key].includes(value)
                    }
                    return item;
                })
            )
        );
    }, [products, filter]);

    useEffect(()=>{
        if(sort ==="newest"){
            setFilteredProducts(prev => 
                [...prev].sort((a,b)=>a.id - b.id)
            )
        } else if(sort ==="mais barato"){
            setFilteredProducts(prev => 
                [...prev].sort((a,b)=>a.preco - b.preco)
            )
        } else{
            setFilteredProducts(prev => 
                [...prev].sort((a,b)=>b.id - a.id)
            )
        }
    }, [sort])

    return (
        <Container>
            {filteredProducts && filteredProducts.map(item => (
                <Product item={item} key={item.id} />
            ))}
        </Container>
    )
}
