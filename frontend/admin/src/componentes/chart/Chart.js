import './Chart.css'
import { LineChart, Line, Tooltip, CartesianGrid, XAxis, YAxis, ResponsiveContainer } from 'recharts';
import { BASE_URL } from '../../utils/baseURL';
import axios from 'axios';
import { useEffect, useState } from 'react';


export default function Chart() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/produto/relatorio/mais-vendidos`);
                setData(response.data)
            } catch { }
        };
        getData();
    }, []);

    return (
        <div className="chart">
            <h3 className="chartTitle">Produtos Mais Vendidos por Quantidade</h3>
            <ResponsiveContainer width="100%" aspect={4 / 1}>
                <LineChart data={data}>
                    <XAxis dataKey="produtoNome"/>
                    <YAxis />
                    <Line type="monotone" dataKey="totalQuantidade" />
                    <Tooltip />
                    <CartesianGrid stroke="#e0dfdf" strokeDasharray="5 5" />
                </LineChart>
            </ResponsiveContainer>
            <h3 className="chartTitle">Produtos Mais Lucrativos</h3>
            <ResponsiveContainer width="100%" aspect={4 / 1}>
                <LineChart data={data}>
                    <XAxis dataKey="produtoNome" />
                    <YAxis />
                    <Line type="monotone" dataKey="totalPreco" />
                    <Tooltip />
                    <CartesianGrid stroke="#e0dfdf" strokeDasharray="5 5" />
                </LineChart>
            </ResponsiveContainer>
        </div>
    )
}
