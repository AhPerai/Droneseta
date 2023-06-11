import Chart from "../../componentes/chart/Chart"
import Info from "../../componentes/info/Info"
import Sidebar from "../../componentes/sidebar/Sidebar"
import Topbar from "../../componentes/topbar/Topbar"
import "./Home.css"

export default function Home() {
    return (
        <>
        <Topbar/>
        <div className="container">
            <Sidebar />
            <div className="home">
                <Info />
                <Chart />
            </div>
        </div>
        </>
    )
}
