import axios from 'axios';
import { useEffect, useState } from 'react';
import { BASE_URL } from '../../utils/baseURL';
import './Info.css'

export default function Info() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/pedido/relatorio`);
                setData(response.data)
            } catch { }
        };
        getData();
    }, []);

    return (
        <div className="info">
            <div className="infoItem">
                <div className="infoTitle">Total de vendas (neste mês):</div>
                <div className="infoValue">
                    <div>{data.totalPreco && data.totalPreco.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL'})}</div>
                </div>
            </div>
            <div className="infoItem">
                <div className="infoTitle">Viagens do Drone (neste mês):</div>
                <div className="infoValue">
                    <div>{data.numeroViagens}</div>
                </div>
            </div>
            <div className="infoItem">
                <div className="infoTitle">Quantidade de Produtos entregue:</div>
                <div className="infoValue">
                    <div>{data.totalPedidos}</div>
                </div>
            </div>
        </div>
    )
}
