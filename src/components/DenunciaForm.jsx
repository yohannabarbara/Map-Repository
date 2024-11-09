import React, { useState } from 'react';

// Função para criar uma denúncia
const createDenuncia = async (denuncia) => {
    try {
        console.log("Enviando dados:", denuncia);  // Logs os dados que estão sendo enviados
        const response = await fetch('http://localhost:8080/api/denuncias', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(denuncia),
        });

        console.log("Status da resposta:", response.status); // Verifica o código de status da resposta

        if (!response.ok) {
            const errorText = await response.text();  // Captura o erro
            console.error("Erro de resposta:", errorText);  // Exibe o erro
            throw new Error(errorText);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro ao enviar denúncia:', error);
        throw error;
    }
};

const DenunciaForm = () => {
    const [titulo, setTitulo] = useState('');
    const [descricao, setDescricao] = useState('');
    const [localizacao, setLocalizacao] = useState('');
    const [data, setData] = useState('');
    const [grauDeImportancia, setGrauDeImportancia] = useState('');
    const [erro, setErro] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Limpar qualquer erro anterior
        setErro('');

        // Objeto com os dados do formulário
        const denuncia = {
            titulo,
            descricao,
            localizacao,
            data,
            grauImportancia: grauDeImportancia,
        };

        try {
            // Chama a função createDenuncia e espera a resposta
            await createDenuncia(denuncia);

            // Limpar os campos após enviar
            setTitulo('');
            setDescricao('');
            setLocalizacao('');
            setData('');
            setGrauDeImportancia('');
            
            alert("Denúncia enviada com sucesso!");
        } catch (error) {
            // Exibe o erro se houver falha
            setErro('Erro ao enviar a denúncia: ' + error.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {erro && <p style={{ color: 'red' }}>{erro}</p>} {/* Exibe erro se houver */}

            <label>Título:</label>
            <input 
                type="text" 
                value={titulo} 
                onChange={(e) => setTitulo(e.target.value)} 
                required 
            />

            <label>Descrição:</label>
            <textarea 
                value={descricao} 
                onChange={(e) => setDescricao(e.target.value)} 
                required 
            ></textarea>

            <label>Localização:</label>
            <input 
                type="text" 
                value={localizacao} 
                onChange={(e) => setLocalizacao(e.target.value)} 
                required 
            />

            <label>Data:</label>
            <input 
                type="date" 
                value={data} 
                onChange={(e) => setData(e.target.value)} 
                required 
            />

            <label>Grau de Importância:</label>
            <select 
                value={grauDeImportancia} 
                onChange={(e) => setGrauDeImportancia(e.target.value)} 
                required 
            >
                <option value="">Selecione</option>
                <option value="Alta">Alta</option>
                <option value="Média">Média</option>
                <option value="Baixa">Baixa</option>
            </select>

            <button type="submit">Enviar Denúncia</button>
        </form>
    );
};

export default DenunciaForm;
