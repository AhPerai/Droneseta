import styled from "styled-components"
import FacebookIcon from '@mui/icons-material/Facebook';
import PinterestIcon from '@mui/icons-material/Pinterest';
import InstagramIcon from '@mui/icons-material/Instagram';
import MapsHomeWorkIcon from '@mui/icons-material/MapsHomeWork';
import PhoneIcon from '@mui/icons-material/Phone';
import EmailIcon from '@mui/icons-material/Email';

const Container = styled.div`
    margin-top: 40px;
    background-color: white;
    display: flex;
`
const Left = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    padding: 20px;
    text-align: center;
`

const Logo = styled.h1``

const Description = styled.p`
    margin: 20px 0px;
    text-align: justify;
    line-height: 1.6;
`
const SocialContainer = styled.div`
    display: flex;
`
const SocialIcon = styled.div`
    width: 40px;
    height: 40px;
    border-radius: 50%;
    color: white;
    background-color: ${props => props.bg}; 
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 20px;
`

const Center = styled.div`
    flex: 1;
    padding: 20px;
    display: flex;
    align-items: center;
    flex-direction: column;
`

const Title = styled.h1`
    margin-bottom: 30px;
`

const List = styled.ul`
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    flex-wrap: wrap;
    text-align: center;
`

const ListItem = styled.li`
    width: 100%;
    margin-bottom: 10px;
    cursor: pointer;
    line-height: 1.6;
`

const Right = styled.div`
    flex: 1;
    padding: 20px;
    text-align: center;
`

const ContactItem = styled.div`
    font-size: 14px;
    margin-bottom: 20px;
    line-height: 1.6;
`

export const Footer = () => {
    return (
        <Container>
            <Left>
                <Title>NAVEGABILIDADE</Title>
                <List>
                    <ListItem>Home</ListItem>
                    <ListItem>Carrinho</ListItem>
                    <ListItem>Moda Masculina</ListItem>
                    <ListItem>Moda Feminina</ListItem>
                    <ListItem>Minha conta</ListItem>
                </List>
            </Left>
            <Center>
                <Logo>DRONESETA</Logo>
                <Description>Bem-vindo à nossa DRONESETA! Na nossa loja, não apenas oferecemos as últimas tendências em roupas, mas também revolucionamos a experiência de compra com entregas de drone. Combinando estilo e conveniência, nossa loja utiliza a mais avançada tecnologia de drones para entregar suas compras diretamente na sua porta, de forma rápida e eficiente.
                </Description>
                <SocialContainer>
                    <SocialIcon bg="#3B5999"><FacebookIcon /></SocialIcon>
                    <SocialIcon bg="#E60023"><PinterestIcon /></SocialIcon>
                    <SocialIcon bg="#E4405F"><InstagramIcon /></SocialIcon>
                </SocialContainer>
            </Center>
            <Right>
                <Title>FALE CONOSCO</Title>
                <ContactItem>
                    <MapsHomeWorkIcon style={{marginRight:"10px"}}/>
                    R. Dr. Getúlio Vargas<br/> Bela Vista, Ibirama - SC, 89140-000
                </ContactItem>
                <ContactItem>
                    <PhoneIcon style={{marginRight:"10px"}}/>
                    (47) 3357-8484
                </ContactItem>
                <ContactItem>
                    <EmailIcon style={{marginRight:"10px"}}/>
                    droneseta@contato.com
                </ContactItem>
            </Right>
        </Container>
    )
}
