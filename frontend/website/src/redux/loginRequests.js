import { loginFailure, loginStart, loginSuccess, logoutUser} from "./userRedux"
import axios from 'axios'
import {BASE_URL} from "../utils/baseURL"

export const login = async (dispatch, user) =>{
    dispatch(loginStart())

    try{
        const response = await axios.post(`${BASE_URL}/login/cliente`,user)
        dispatch(loginSuccess(response.data))
    }catch(error){
        dispatch(loginFailure())
    }
}

export const logout = (dispatch) =>{
    dispatch(logoutUser())
}