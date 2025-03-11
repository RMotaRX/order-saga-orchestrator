CREATE TYPE status_pagamento AS ENUM ('PENDENTE', 'SUCESSO', 'REEMBOLSO');

CREATE TABLE public.pagamentos (
  id SERIAL PRIMARY KEY,
  pedido_id INTEGER NOT NULL,
  transacao_id INTEGER NOT NULL,
  total_itens INTEGER NOT NULL,
  valor_total NUMERIC(10,2) NOT NULL,
  status status_pagamento NOT NULL,
  criado_em TIMESTAMP NOT NULL,
  atualizado_em TIMESTAMP NOT NULL
);