import "./Sidebar.css"
import TimelineIcon from '@mui/icons-material/Timeline';
import HomeIcon from '@mui/icons-material/Home'
import TableRowsIcon from '@mui/icons-material/TableRows';
import AddBoxOutlinedIcon from '@mui/icons-material/AddBoxOutlined';
import StorefrontIcon from '@mui/icons-material/Storefront';
import InventoryIcon from '@mui/icons-material/Inventory';


export default function Sidebar() {
    return (
        <div className="sidebar">
            <div className="sidebarWrapper">
                <div className="sidebarMenu">
                    <div className="sidebarTitle"><HomeIcon className="sidebarIcon"/><h3>Home</h3></div>
                    <ul className="sidebarList">
                        <li className="sidebarListItem">
                            <StorefrontIcon className="sidebarIcon"/>Info
                        </li>
                        <li className="sidebarListItem">
                            <TimelineIcon className="sidebarIcon"/>Vendas - Dashboards
                        </li>
                    </ul>
                </div>

                <div className="sidebarMenu">
                    <div className="sidebarTitle"><InventoryIcon className="sidebarIcon"/><h3>Produtos</h3></div>
                    <ul className="sidebarList">
                        <li className="sidebarListItem">
                            <TableRowsIcon className="sidebarIcon"/> Tabela 
                        </li>
                        <li className="sidebarListItem">
                            <AddBoxOutlinedIcon className="sidebarIcon"/> Adicionar Produto
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}
