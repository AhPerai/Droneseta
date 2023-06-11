import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
    name: "cart",
    initialState:{
        products: [],
        quantity: 0,
        totalPrice: 0,
    },
    reducers:{
        addProduct: (state, action) => {
            const addedItem = state.products.find((productCart) => productCart.id === action.payload.id);
            if(!addedItem){
                state.quantity += 1
                state.products.push(action.payload)
                state.totalPrice += action.payload.preco * action.payload.qtdPedida
            }else{
                const sum = state.products.reduce((sum, productCart) => {
                    if(productCart.id === action.payload.id){
                        return sum + productCart.qtdPedida
                    }
                    return sum
                }, 0);
            
                if(action.payload.quantidade >= (sum + action.payload.qtdPedida) ){
                    action.payload.qtdPedida = sum + action.payload.qtdPedida
                    state.totalPrice = (action.payload.preco * action.payload.qtdPedida)
                    state.products[state.products.indexOf(addedItem)] = action.payload
                }
            }
        },
        resetCart: (state) => {
            state.products = [];
            state.quantity = 0;
            state.totalPrice = 0;
        }
    }
})

export const { addProduct, resetCart } = cartSlice.actions
export default  cartSlice.reducer;