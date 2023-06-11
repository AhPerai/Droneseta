import styled from "styled-components"
import { useState } from "react";
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import { carouselItems } from '../data'


const Container = styled.div`
    width:100%;
    height:100vh;
    display: flex;
    position: relative;
    overflow: hidden;
`

const Wrapper = styled.div`
    height: 100%;
    display: flex;
    align-items: center;
    transition: all 1.5s ease;
    transform: translateX(${props => props.index * -100}vw);
`

const Slider = styled.div`
    display: flex; 
    align-items: center;
    width: 100vw;
    height: 100vh;
    background-color: ${props => props.bg}
`

const Arrow = styled.div`
    width: 50px;
    height: 50px;
    background-color: #fff7f7;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0; 
    bottom: 0;
    left: ${(props) => props.direction === 'left' && '10px'};
    right: ${(props) => props.direction === 'right' && '10px'};
    margin: auto;
    cursor: pointer;
    opacity: 0.5;
    z-index: 2;
`

const ImgContainer = styled.div`
    height: 100%;
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
`

const Image = styled.img`
    height: 80%;
`
const InfoContainer = styled.div`
    flex: 1;
    padding: 50px; 
    min-height: 50%;
`

const Title = styled.h1`
    font-size: 70px;
`

const Description = styled.p`
    margin: 50px 0px;
    font-size: 20px;
    font-weight: bold;
    letter-spacing: 3px;
`

const Button = styled.button`
    padding: 15px;
    background: black;
    color: #fff7f7;
    font-size: 20px;
    border: none;
    cursor: pointer;
`

export const Carousel = () => {

    const [slideIndex, setSlideIndex] = useState(0);

    const slide = (direction) =>{
        
        if(direction === "left"){
            setSlideIndex(slideIndex > 0 ? slideIndex -1 : carouselItems.length -1)
        }else{
            setSlideIndex(slideIndex < carouselItems.length -1 ? slideIndex +1 : 0)            
        }
    }

    return (
        <Container>
            <Arrow direction="left" onClick={() =>slide("left")}>
                <ArrowLeftOutlinedIcon />
            </Arrow>
            <Wrapper index = {slideIndex}>
                {carouselItems.map(item =>(
                <Slider bg={item.bg} key={item.id}>
                    <ImgContainer>
                        <Image src={item.img}></Image>
                    </ImgContainer>
                    <InfoContainer>
                        <Title>{item.title}</Title>
                        <Description>{item.desc}</Description>
                        <Button>CONFERIR AGORA!</Button>
                    </InfoContainer>
                </Slider>
                ))}
            </Wrapper>
            <Arrow direction="right" onClick={() =>slide("right")}>
                <ArrowRightOutlinedIcon />
            </Arrow>

        </Container>
    )
}
