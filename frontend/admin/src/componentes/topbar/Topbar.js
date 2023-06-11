import React from 'react'
import "./Topbar.css"
import { ReactComponent as Drone } from '../../assets/images/droneseta_icon.svg';
import { logout } from '../../redux/loginRequest'
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

export default function Topbar() {
    const admin = useSelector(state => state.user.loggedUser);
    const dispatch = useDispatch()

    const handleClick = (e) => {
        logout(dispatch)
    }
    return (
        <div className="topbar">
            <div className="topbarWrapper">
                <div className="topLeft">
                    <div className="logoDrone"><Drone /></div>
                    <span className="logo">ADM - DRONESETA</span>
                </div>
                {admin &&
                    <div className="topRight">
                        <span className="login">

                            <Link to="/login" style={{ textDecoration: 'none' }}>
                                <span onClick={handleClick}>SAIR</span>
                            </Link>

                        </span>
                    </div>
                }
            </div>
        </div>
    )
}