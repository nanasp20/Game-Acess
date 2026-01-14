USE gameaccessdb;
DROP TABLE IF EXISTS aluguel;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS jogo;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
    );
    
    
 CREATE TABLE jogo (
    id_jogo INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    genero VARCHAR(50),
    desenvolvedora VARCHAR(100),
    distribuidora VARCHAR(100),
    data_lancamento DATE,
    classificacao VARCHAR(20),
    descricao TEXT,
    imagem VARCHAR(200), -- opcional: caminho ou nome do arquivo de imagem
    disponibilidade BOOLEAN DEFAULT TRUE
);   

INSERT INTO jogo (titulo, genero, desenvolvedora, distribuidora, data_lancamento, classificacao, descricao, imagem)
VALUES
('Tinker Lands', 'Aventura', 'Studio Indie', 'IndieGames Inc.', '2023-06-15', 'Livre', 'Explore terras mágicas com personagens carismáticos.', 'tinkerlands.jpg'),

('Rematch', 'Esporte', 'GoalDev Studios', 'ProGames', '2022-10-12', 'Livre', 'Simulador de futebol com partidas intensas.', 'rematch.jpg'),

('Pistolero', 'Ação', 'Shooter Studio', 'Action Games Ltd.', '2024-02-20', '16+', 'Ação frenética com armas em ambientes urbanos.', 'pistolero.jpg'),

('Helldivers II', 'Tiro', 'Arrowhead Game Studios', 'PlayStation Studios', '2024-02-08', '18+', 'Tática cooperativa em um universo alienígena.', 'helldivers2.jpg'),

('Among Us', 'Casual', 'Innersloth', 'Innersloth', '2018-06-15', '10+', 'Descubra o impostor em partidas multiplayer.', 'amongus.jpg'),

('9Kings', 'Estratégia', 'Royal Devs', 'Kingdom Games', '2023-09-30', '12+', 'Conquiste reinos e monte seu império.', '9kings.jpg'),

('Peak', 'Plataforma', 'SkyTeam Games', 'Pixel Studios', '2023-04-10', 'Livre', 'Aventuras nas montanhas com desafios únicos.', 'peak.jpg'),

('Enshrouded', 'RPG', 'Keen Games', 'Keen Games', '2024-01-24', '16+', 'RPG de sobrevivência em mundo aberto.', 'enshrouded.jpg'),

('Shadow Realms: Worlds Beyond', 'RPG', 'DarkForge', 'Shadowline Studios', '2022-11-03', '18+', 'Viaje entre mundos sombrios e mágicos.', 'shadowrealms.jpg'),

('Satisfactory', 'Simulação', 'Coffee Stain Studios', 'Coffee Stain Publishing', '2020-06-08', 'Livre', 'Construa fábricas gigantes com automação e eficiência.', 'satisfactory.jpg'),

('No Man\'s Sky', 'Exploração', 'Hello Games', 'Hello Games', '2016-08-12', '10+', 'Explore um universo infinito com criaturas e planetas únicos.', 'nomanssky.jpg'),

('Electric Sim', 'Simulação', 'PowerSim Studio', 'Electric Devs', '2023-03-22', 'Livre', 'Gerencie e otimize redes elétricas complexas.', 'electricsim.jpg');

SELECT * FROM jogo;
