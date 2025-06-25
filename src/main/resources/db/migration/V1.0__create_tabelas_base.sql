CREATE TABLE prescricao_medica (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

INSERT INTO prescricao_medica (descricao) VALUES
('Prescrição para tratamento de hipertensão'),
('Prescrição para acompanhamento pediátrico');

CREATE TABLE pacientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    convenio VARCHAR(100) NOT NULL,
    residencia VARCHAR(100) NOT NULL,
    nascimento DATE NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

INSERT INTO pacientes (nome, cpf, convenio, residencia, nascimento, telefone) VALUES
('Maria Silva', '12345678901', 'Unimed', 'Rua das Flores, 123', '1985-06-15', '(11) 91234-5678'),
('João Souza', '10987654321', 'Bradesco Saúde', 'Av. Paulista, 1000', '1990-11-30', '(21) 99876-5432'),
('Ana Oliveira', '19283746500', 'SUS', 'Rua do Sol, 456', '1978-02-25', '(31) 98765-4321');

CREATE TABLE medicos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO medicos (nome, cpf, especialidade, crm, telefone, email) VALUES
('Dr. Carlos Lima', '32165498700', 'Cardiologia', 'CRM123456', '(11) 91234-5678', 'carlos.lima@gmail.com'),
('Dra. Fernanda Souza', '98765432100', 'Dermatologia', 'CRM654321', '(21) 99876-5432', 'fernanda.souza@outlook.com'),
('Dr. João Pereira', '45678912300', 'Ortopedia', 'CRM789123', '(31) 98765-4321', 'joao.pereira@unimed.com.br');

CREATE TABLE secretarias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    setor VARCHAR(100) NOT NULL
);

INSERT INTO secretarias (nome, cpf, email, telefone, setor) VALUES
('Julia Martins', '56473829100', 'julia.martins@yahoo.com.br', '(11) 91234-5678', 'Agendamento'),
('Carlos Almeida', '29384756100', 'carlos.almeida@gmail.com', '(21) 99876-5432', 'Recepção'),
('Fernanda Costa', '74839261500', 'fernanda.costa@gmail.com', '(31) 98765-4321', 'Financeiro');

CREATE TABLE consultas (
    id SERIAL PRIMARY KEY,
    dia DATE NOT NULL,
    hora TIME NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    id_medico_responsavel INT NOT NULL,
    id_prescricao_medica INT NOT NULL,
    FOREIGN KEY (id_medico_responsavel) REFERENCES medicos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_prescricao_medica) REFERENCES prescricao_medica(id) ON DELETE CASCADE
);

INSERT INTO consultas (dia, hora, preco, id_medico_responsavel, id_prescricao_medica) VALUES
('2025-05-01', '09:30:00', 150.00, 1, 1),
('2025-01-02', '14:30:00', 200.00, 2, 2),
('2025-02-03', '11:15:00', 120.00, 1, 1);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL,
    enabled BOOLEAN NOT NULL,
    CONSTRAINT unq_user_name UNIQUE(user_name)
);

INSERT INTO users (full_name, user_name, role, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
('admin', 'admin@gmail.com', 'ADMIN', '$2a$12$o7Zxzw8qcLGupsu1wfGeMOCUBS0fupXXxoOXux5px3QtGPVSRNNEO', true, true, true, true),
('usuario', 'usuario@gmail.com', 'USER', '$2a$12$86BZPbXgZcTa0rS/zlEHauWafnplDrswsUrgnIKEx3Am6ofstJUvS', true, true, true, true);
-- user: usuario senha: usuario     seguranca completa
-- user: admin  senha: admin                KSKSK

