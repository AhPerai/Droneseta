import imgCarousel1 from './assets/images/Carousel/carousel-1.png';
import imgCarousel2 from './assets/images/Carousel/carousel-2.png';
import imgCarousel3 from './assets/images/Carousel/carousel-3.png';
import Camisetas from './assets/images//Categories/Camisetas.jpg';
import Casacos from './assets/images/Categories/Casacos.jfif';
import Jaquetas from './assets/images/Categories/Jaquetas.png';

export const carouselItems = [
    {
        id: 1, 
        img: imgCarousel1,
        title: `OUTONO\nINVERNO 2023`,
        desc: 'ENCANTE-SE COM A ELEGÂNCIA FEMININA DA TEMPORADA OUTONO/INVERNO 2023!',
        bg: '#F7D5D5',
        circle_color: 'rgba(231, 241, 242, 0.8)'
    },
    {
        id: 2, 
        img: imgCarousel2,
        title: 'CONFIRA NOSSAS NOVIDADES!',
        desc: 'DE ROUPAS SOFISTICADS E ELEGANTES A MODA CASUAL\n TEMOS O QUE COMBINA COM VOCÊ',
        bg: '#97C7A8',
        circle_color: 'rgba(231, 241, 242, 0.8)'
    }, 
    {
        id: 3, 
        img: imgCarousel3,
        title: 'MODA MASCULINA\nOUTONO/INVERNO',
        desc: 'EXPLORE NOVAS POSSIBILIDADES DE ESTILO COM AS TENDÊNCIAS MASCULINAS PARA A TEMPORADA MAIS FRIA DO ANO',
        bg: '#c1c1e180',
        circle_color: 'rgba(231, 241, 242, 0.8)'
    }, 
]

export const categories = [
    {
        id: 1,
        img: Camisetas,
        title: 'CAMISETAS',
    },
    {
        id: 2,
        img: Casacos,
        title: 'CASACOS',
    },
    {
        id: 3,
        img: Jaquetas,
        title: 'JAQUETAS',
    }
]

export const signUpData = ['Dados Pessoais', 'Endereço', 'Cartão']
