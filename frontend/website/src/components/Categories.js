import styled from "styled-components"
import { categories } from '../data'
import CategoryItem from './CategoryItem'

const Container = styled.div`
    display: flex;
    padding: 10px 15px;
    justify-content: space-between;
`

export const Categories = () => {
    return (
        <div>
            <Container>
                {categories.map(item => (
                    <CategoryItem item={item} key={item.id}/>
                ))}
            </Container>
        </div>
    )
}
