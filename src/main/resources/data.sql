-- Inserindo usuário admin
INSERT INTO users(name, username, password, enabled) VALUES('Admin', 'admin@email.com.br', '$2a$10$I0hNev65Kkv4VfETs0dMmuf3nbZDooKYkPcGnPsLjvkhMYfBKe7Oi', true)
INSERT INTO users(name, username, password, enabled) VALUES('user', 'user@email.com', '$2a$10$I0hNev65Kkv4VfETs0dMmuf3nbZDooKYkPcGnPsLjvkhMYfBKe7Oi', true)

-- Inserindo Authorities
INSERT INTO authorities(username, authority) VALUES('admin@email.com.br', 'ADMIN')
INSERT INTO authorities(username, authority) VALUES('user@email.com', 'USER')

-- Inserindo os Bancos
INSERT INTO bank(id, name) VALUES (001, 'BANCO DO BRASIL')
INSERT INTO bank(id, name) VALUES (237, 'BANCO BRADESCO')
INSERT INTO bank(id, name) VALUES (260, 'NUBANK')
INSERT INTO bank(id, name) VALUES (380, 'PICPAY')
INSERT INTO bank(id, name) VALUES (323, 'MERCADO PAGO')
INSERT INTO bank(id, name) VALUES (077, 'BANCO INTER')
INSERT INTO bank(id, name) VALUES (341, 'ITAÚ UNIBANCO')
INSERT INTO bank(id, name) VALUES (104, 'CAIXA ECONÔMICA FEDERAL')
INSERT INTO bank(id, name) VALUES (033, 'BANCO SANTANDER')
INSERT INTO bank(id, name) VALUES (041, 'BANRISUL')
INSERT INTO bank(id, name) VALUES (422, 'BANCO SAFRA')
INSERT INTO bank(id, name) VALUES (748, 'BANCO SICREDI')
INSERT INTO bank(id, name) VALUES (121, 'BANCO AGIBANK')
INSERT INTO bank(id, name) VALUES (208, 'BANCO BTG PACTUAL')
INSERT INTO bank(id, name) VALUES (623, 'BANCO PAN')
INSERT INTO bank(id, name) VALUES (069, 'BANCO CREFISA')
INSERT INTO bank(id, name) VALUES (336, 'BANCO C6')