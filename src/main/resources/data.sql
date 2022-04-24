-- Inserindo usuário admin
INSERT INTO users(name, username, password, enabled) VALUES('Admin', 'admin@email.com', '$2a$10$SlPvWc6mCqUvEF0MgzZD5el/kembXDY9A138H.tDal/s4rzmHP4FO', true)

-- Inserindo Authorities
INSERT INTO authorities(username, authority) VALUES('admin@email.com', 'USER')
-- INSERT INTO role(name) VALUES('ROLE_ADMIN')

-- INSERT INTO user_roles(user_id, roles_id) VALUES(1, 1)
-- INSERT INTO user_roles(user_id, roles_id) VALUES(1, 2)

-- -- Inserindo os Bancos no banco de dados.
INSERT INTO bank(id, name) VALUES (654, 'A.J. RENNER S.A.')
-- INSERT INTO bank(id, name) VALUES (246, 'ABC-BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (213, 'ARBI S.A.')
-- INSERT INTO bank(id, name) VALUES (19, 'AZTECA DO BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (25, 'BANCO ALFA')
-- INSERT INTO bank(id, name) VALUES (241, 'Banco Classico S.A')
-- INSERT INTO bank(id, name) VALUES (83, 'BANCO DA CHINA BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (300, 'BANCO DE LA NACION ARGENTINA')
-- INSERT INTO bank(id, name) VALUES (495, 'BANCO DE LA PROVINCIA DE BUENOS AIRES')
-- INSERT INTO bank(id, name) VALUES (494, 'BANCO DE LA REPUBLICA ORIENTAL DEL URUGUAY')
-- INSERT INTO bank(id, name) VALUES (1, 'BANCO DO BRASIL')
-- INSERT INTO bank(id, name) VALUES (37, 'BANCO DO ESTADO DO PARÁ S.A')
-- INSERT INTO bank(id, name) VALUES (456, 'BANCO TOKYO MITSUBISH UFJ BRASIL S.A')
-- INSERT INTO bank(id, name) VALUES (370, 'BANCO WESTLB DO BRASIL')
-- INSERT INTO bank(id, name) VALUES (756, 'BANCOOB')
-- INSERT INTO bank(id, name) VALUES (47, 'BANESE')
-- INSERT INTO bank(id, name) VALUES (33, 'BANESPA')
-- INSERT INTO bank(id, name) VALUES (21, 'BANESTES')
-- INSERT INTO bank(id, name) VALUES (719, 'BANIF-BANCO INTERNACIONAL DO FUNCHAL (BRASIL) S.A')
-- INSERT INTO bank(id, name) VALUES (755, 'BANK OF AMERICA MERRILL LYNCH BANCO MULTIPLO S.A.')
-- INSERT INTO bank(id, name) VALUES (41, 'BANRISUL')
-- INSERT INTO bank(id, name) VALUES (740, 'BARCLAYS S.A.')
-- INSERT INTO bank(id, name) VALUES (3, 'BASA')
-- INSERT INTO bank(id, name) VALUES (107, 'BBM S.A')
-- INSERT INTO bank(id, name) VALUES (81, 'BBN BANCO BRASILEIRO DE NEGOCIOS S.A')
-- INSERT INTO bank(id, name) VALUES (250, 'BCV - BANCO DE CREDITO E VAREJO S.A')
-- INSERT INTO bank(id, name) VALUES (36, 'BEM')
-- INSERT INTO bank(id, name) VALUES (122, 'BERJ S.A')
-- INSERT INTO bank(id, name) VALUES (78, 'BES INVESTIMENTO DO BRASIL SA - BANCO DE INVESTIM.')
-- INSERT INTO bank(id, name) VALUES (739, 'BGN S.A.')
-- INSERT INTO bank(id, name) VALUES (320, 'BIC BANCO')
-- INSERT INTO bank(id, name) VALUES (96, 'BM&F DE SERV. DE LIQUIDACAO E CUSTODIA S.A')
-- INSERT INTO bank(id, name) VALUES (394, 'BMC S.A.')
-- INSERT INTO bank(id, name) VALUES (318, 'BMG S.A.')
-- INSERT INTO bank(id, name) VALUES (4, 'BNB')
-- INSERT INTO bank(id, name) VALUES (752, 'BNP PARIBAS BRASIL S.A')
-- INSERT INTO bank(id, name) VALUES (17, 'BNY MELLON S.A.')
-- INSERT INTO bank(id, name) VALUES (248, 'BOA VISTA INTERATLANTICO S.A')
-- INSERT INTO bank(id, name) VALUES (218, 'BONSUCESSO S.A.')
-- INSERT INTO bank(id, name) VALUES (69, 'BPN BRASIL BANCO MULTIPLO S.A')
-- INSERT INTO bank(id, name) VALUES (65, 'BRACCE S.A.')
-- INSERT INTO bank(id, name) VALUES (237, 'BRADESCO')
-- INSERT INTO bank(id, name) VALUES (225, 'BRASCAN S.A.')
-- INSERT INTO bank(id, name) VALUES (125, 'BRASIL PLURAL S.A. BANCO MULTIPLO')
-- INSERT INTO bank(id, name) VALUES (70, 'BRB')
-- INSERT INTO bank(id, name) VALUES (92, 'BRICKELL S A CREDITO, FINANCIAMENTO E INVESTIMENTO')
-- INSERT INTO bank(id, name) VALUES (208, 'BTG PACTUAL S.A.')
-- INSERT INTO bank(id, name) VALUES (263, 'CACIQUE S.A.')
-- INSERT INTO bank(id, name) VALUES (104, 'CAIXA ECON. FEDERAL')
-- INSERT INTO bank(id, name) VALUES (473, 'CAIXA GERAL - BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (412, 'CAPITAL S.A.')
-- INSERT INTO bank(id, name) VALUES (40, 'CARGILL S.A')
-- INSERT INTO bank(id, name) VALUES (112, 'CC UNICRED BRASIL CENTRAL')
-- INSERT INTO bank(id, name) VALUES (84, 'CC UNIPRIME NORTE DO PARANA')
-- INSERT INTO bank(id, name) VALUES (114, 'CECOOPES-CENTRAL DAS COOP DE ECON E CRED MUTUO DO')
-- INSERT INTO bank(id, name) VALUES (85, 'CECREDI')
-- INSERT INTO bank(id, name) VALUES (266, 'EDULA S.A.')
-- INSERT INTO bank(id, name) VALUES (233, 'CIFRA S.A.')
-- INSERT INTO bank(id, name) VALUES (745, 'CITIBANK')
-- INSERT INTO bank(id, name) VALUES (477, 'Citibank N.A.')
-- INSERT INTO bank(id, name) VALUES (90, 'COOPERATIVA CENTRAL DE CREDITO DO ESTADO DE SP')
-- INSERT INTO bank(id, name) VALUES (97, 'COOPERATIVA CENTRAL DE CREDITO NOROESTE BRASILEIRO')
-- INSERT INTO bank(id, name) VALUES (89, 'COOPERATIVA DE CREDITO RURAL DA REGIAO DA MOGIANA')
-- INSERT INTO bank(id, name) VALUES (75, 'CR2 S.A')
-- INSERT INTO bank(id, name) VALUES (98, 'CREDIALIANCA COOPERATIVA DE CREDITO RURAL')
-- INSERT INTO bank(id, name) VALUES (222, 'CREDIT AGRICOLE BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (505, 'CREDIT SUISSE (BRASIL) S.A.')
-- INSERT INTO bank(id, name) VALUES (707, 'DAYCOVAL')
-- INSERT INTO bank(id, name) VALUES (487, 'DEUTSCHE BANK S. A. - BANCO ALEMAO')
-- INSERT INTO bank(id, name) VALUES (214, 'DIBENS S.A.')
-- INSERT INTO bank(id, name) VALUES (265, 'FATOR S.A.')
-- INSERT INTO bank(id, name) VALUES (224, 'FIBRA')
-- INSERT INTO bank(id, name) VALUES (626, 'FICSA S.A.')
-- INSERT INTO bank(id, name) VALUES (121, 'GERADOR S.A.')
-- INSERT INTO bank(id, name) VALUES (612, 'GUANABARA S.A.')
-- INSERT INTO bank(id, name) VALUES (62, 'HIPERCARD BANCO MULTIPLO S.A')
-- INSERT INTO bank(id, name) VALUES (399, 'HSBC')
-- INSERT INTO bank(id, name) VALUES (63, 'IBI')
-- INSERT INTO bank(id, name) VALUES (604, 'INDUSTRIAL DO BRASIL S. A.')
-- INSERT INTO bank(id, name) VALUES (653, 'INDUSVAL S.A.')
-- INSERT INTO bank(id, name) VALUES (492, 'ING BANK N.V.')
-- INSERT INTO bank(id, name) VALUES (630, 'INTERCAP S.A.')
-- INSERT INTO bank(id, name) VALUES (77, 'INTERMEDIUM S.A.')
-- INSERT INTO bank(id, name) VALUES (249, 'Investcred Unibanco')
-- INSERT INTO bank(id, name) VALUES (341, 'ITAÚ')
-- INSERT INTO bank(id, name) VALUES (652, 'ITAU HOLDING FINANCEIRA S.A')
-- INSERT INTO bank(id, name) VALUES (184, 'Itaú-BBA')
-- INSERT INTO bank(id, name) VALUES (74, 'J. SAFRA S.A.')
-- INSERT INTO bank(id, name) VALUES (376, 'J.P. MORGAN S.A.')
-- INSERT INTO bank(id, name) VALUES (217, 'JOHN DEERE S.A.')
-- INSERT INTO bank(id, name) VALUES (488, 'JPMORGAN CHASE BANK')
-- INSERT INTO bank(id, name) VALUES (76, 'KDB DO BRASIL S.A')
-- INSERT INTO bank(id, name) VALUES (757, 'KEB DO BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (600, 'Luso Brasileiro')
-- INSERT INTO bank(id, name) VALUES (243, 'MAXIMA S.A.')
-- INSERT INTO bank(id, name) VALUES (389, 'MERCANTIL DO BRASIL')
-- INSERT INTO bank(id, name) VALUES (746, 'MODAL S.A.')
-- INSERT INTO bank(id, name) VALUES (66, 'MORGAN STANLEY DEAN WITTER S.A')
-- INSERT INTO bank(id, name) VALUES (14, 'NATIXIS BRASIL S.A. - BANCO MòLTIPLO')
-- INSERT INTO bank(id, name) VALUES (753, 'NBC BANK BRASIL S.A.- BANCO MULTIPLO')
-- INSERT INTO bank(id, name) VALUES (45, 'OPPORTUNITY S.A.')
-- INSERT INTO bank(id, name) VALUES (79, 'ORIGINAL DO AGRONEGOCIO S.A.')
-- INSERT INTO bank(id, name) VALUES (212, 'ORIGINAL S.A.')
-- INSERT INTO bank(id, name) VALUES (623, 'PANAMERICANO')
-- INSERT INTO bank(id, name) VALUES (254, 'PARANA BANCO S.A.')
-- INSERT INTO bank(id, name) VALUES (611, 'PAULISTA')
-- INSERT INTO bank(id, name) VALUES (613, 'PECUNIA S.A.')
-- INSERT INTO bank(id, name) VALUES (94, 'PETRA S.A.')
-- INSERT INTO bank(id, name) VALUES (643, 'PINE S.A.')
-- INSERT INTO bank(id, name) VALUES (735, 'POTTENCIAL S.A.')
-- INSERT INTO bank(id, name) VALUES (747, 'RABOBANK INTERNATIONAL BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (88, 'RANDON S.A.')
-- INSERT INTO bank(id, name) VALUES (633, 'RENDIMENTO S.A.')
-- INSERT INTO bank(id, name) VALUES (741, 'RIBEIRÃO PRETO')
-- INSERT INTO bank(id, name) VALUES (120, 'RODOBENS S.A')
-- INSERT INTO bank(id, name) VALUES (453, 'RURAL')
-- INSERT INTO bank(id, name) VALUES (72, 'RURAL MAIS S.A')
-- INSERT INTO bank(id, name) VALUES (422, 'SAFRA')
-- INSERT INTO bank(id, name) VALUES (751, 'SCOTIABANK BRASIL S.A BANCO MULTIPLO')
-- INSERT INTO bank(id, name) VALUES (743, 'SEMEAR S.A.')
-- INSERT INTO bank(id, name) VALUES (748, 'SICREDI')
-- INSERT INTO bank(id, name) VALUES (749, 'SIMPLES S.A.')
-- INSERT INTO bank(id, name) VALUES (366, 'SOCIETE GENERALE BRASIL S.A')
-- INSERT INTO bank(id, name) VALUES (637, 'SOFISA S.A.')
-- INSERT INTO bank(id, name) VALUES (464, 'SUMITOMO MITSUI BRASILEIRO S.A.')
-- INSERT INTO bank(id, name) VALUES (82, 'TOPAZIO S.A.')
-- INSERT INTO bank(id, name) VALUES (634, 'Triangulo')
-- INSERT INTO bank(id, name) VALUES (230, 'UNICARD BANCO MULTIPLO S.A')
-- INSERT INTO bank(id, name) VALUES (91, 'UNICRED CENTRAL RS - CENTRAL DE COOP ECON CRED MUT')
-- INSERT INTO bank(id, name) VALUES (87, 'UNICRED CENTRAL SANTA CATARINA')
-- INSERT INTO bank(id, name) VALUES (99, 'UNIPRIME CENTRAL - CENTRAL INT DE COOP DE CRED LTD')
-- INSERT INTO bank(id, name) VALUES (655, 'VOTORANTIM')
-- INSERT INTO bank(id, name) VALUES (610, 'VR S.A.')
-- INSERT INTO bank(id, name) VALUES (119, 'WESTERN UNION DO BRASIL S.A.')
-- INSERT INTO bank(id, name) VALUES (124, 'WOORI BANK DO BRASIL S.A')