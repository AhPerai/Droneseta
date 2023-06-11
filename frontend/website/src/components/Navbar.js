import styled from "styled-components"
import Badge from '@mui/material/Badge'
import CartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router-dom";
import { logout } from "../redux/loginRequests"
import { categories } from '../data'
import { ReactComponent as Drone } from '../assets/images/droneseta_icon.svg';

/*____________CONTAINER DA BARRA DE NAVERAÇÃO__________*/
const NavContainer = styled.div`
    height: 60px;
    background-color: white;
`

const Wrapper = styled.div`
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
`
/*____________SEGMENTOS DO CONTAINER__________*/
const Left = styled.div`
    flex: 1;
    display: flex; 
    align-items: center;
`
const Center = styled.div`
    flex: 3;
    display:flex;
    align-items: center;
    justify-content: flex-start;
`

const Right = styled.div`
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: flex-end;
`

const Logo = styled.h1`
    font-weight:bold;
`
/*____________Componentes do  Segmento Um__________*/

const LogoDroneseta = styled(Drone)`
  height: 2.5em; 
  width: 4em;
  cursor: pointer;
`;

/*____________Componentes do  Segmento Dois__________*/

const CategoriaMenu = styled.div`
    font-size: .8rem;
    color: black !important;
    font-weight: bold;
    margin: 0 0.6875rem;
    padding: 15px 0!important;
    border-bottom: 2px solid transparent;
    cursor: pointer;
    transition: 0.25s all ease; 
    &:hover {
        border-bottom: 2px solid black;
      }
`



/*____________Componentes do  Segmento Três__________*/
const ItemMenu = styled.div`
    font-size: 14px;
    cursor: pointer;
    margin-left: 25px;
    color: black !important;
`

export const Navbar = () => {
    const quantity = useSelector(state => state.cart.quantity)
    const user = useSelector(state => state.user.loggedUser);
    const dispatch = useDispatch()

    const handleClick = (e) => {
        logout(dispatch)
    }

    return (
        <NavContainer>
            <Wrapper>
                <Left>
                <Link to="/"><LogoDroneseta /></Link>
                    <Logo>DRONESETA.</Logo>
                </Left>
                <Center>
                    {categories.map(item => (
                        <Link  key={item.id} style={{ textDecoration: 'none' }}
                            to={`/produtos/${item.title.toLowerCase()}`}>
                            <CategoriaMenu >{item.title}</CategoriaMenu>
                        </Link>
                    ))}
                </Center>
                <Right>
                    {user ?
                        <Link to="/" style={{ textDecoration: 'none' }}>
                            <ItemMenu onClick={handleClick}>SAIR</ItemMenu>
                        </Link> :
                        <>
                            <Link to="/Cadastro" style={{ textDecoration: 'none' }}><ItemMenu>CADASTRAR-SE</ItemMenu></Link>
                            <Link to="/Login" style={{ textDecoration: 'none' }}><ItemMenu>ENTRAR</ItemMenu></Link>
                        </>
                    }
                    <Link to="/carrinho">
                        <ItemMenu>
                            <Badge badgeContent={quantity} color="primary">
                                <CartOutlinedIcon color="action" />
                            </Badge>
                        </ItemMenu>
                    </Link>
                </Right>
            </Wrapper>
        </NavContainer>
    )
}

