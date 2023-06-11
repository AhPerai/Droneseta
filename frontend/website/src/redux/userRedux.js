import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: "user",
    initialState:{
        loggedUser: null,
        fetchingData: false,
        error: false
    },
    reducers:{
        loginStart: (state) => {
            state.fetchingData = true
            state.error = false
        },
        loginSuccess: (state, action) => {
            state.fetchingData = false
            state.loggedUser = action.payload
        },
        loginFailure: (state) => {
            state.fetchingData = false;
            state.error = true;
        },
        logoutUser: (state, action) => {
            state.loggedUser = null
        },
    }
})

export const { loginStart, loginSuccess, loginFailure, logoutUser } = userSlice.actions
export default  userSlice.reducer;