// src/api/api.js
const API_URL = "http://localhost:8080/api/denuncias";

// Função para buscar todas as denúncias
export const fetchDenuncias = async () => {
    const response = await fetch(API_URL);
    return response.json();
};

// Função para buscar uma denúncia pelo ID
export const fetchDenunciaById = async (id) => {
    const response = await fetch(`${API_URL}/${id}`);
    return response.json();
};

// Função para criar uma nova denúncia
export const createDenuncia = async (denuncia) => {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(denuncia)
    });
    return response.json();
};

// Função para deletar uma denúncia pelo ID
export const deleteDenuncia = async (id) => {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
};
