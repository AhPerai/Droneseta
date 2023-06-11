import styled from "styled-components"

const AnuncioContainer = styled.div`
    height: 30px;
    background-color: teal;
    color: white;
    font-size: 14px;
    font-weight: 500;
    display: flex;
    align-items: center;
    justify-content: center;
`

export const Anuncio = () => {
    return (
        <AnuncioContainer>
            <span style={{marginRight: 10}}>Promoção de Outono Inverno!</span> 
            <span>Receba suas roupas na porta da sua casa como num passe de mágica!</span>
        </AnuncioContainer>
    )
}
