CREATE TABLE public.estoque (
    id SERIAL PRIMARY KEY,
    cod_produto VARCHAR(50) NOT NULL,
    quantidade INTEGER NOT NULL
);