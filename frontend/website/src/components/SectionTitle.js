import styled from "styled-components"

const Container = styled.div`
    height: 80px;
    text-align: center;
    padding: 10px;
`

const Title = styled.h1`
    font-size: 50px;
    color: black;
    line-height: 80px;
`

export const SectionTitle = ({title}) => {
    return (
        <Container>
            <Title>{title}</Title>
        </Container>
    )
}
