
CREATE TABLE Supplier (
                Id INTEGER NOT NULL,
                CompanyName VARCHAR(100) NOT NULL,
                TradingName VARCHAR(120) NOT NULL,
                Cnpj VARCHAR(14) NOT NULL,
                PhoneNumber CHAR(11),
                EmailAddress VARCHAR(120),
                PublicArea VARCHAR(100) NOT NULL,
                District VARCHAR(80) NOT NULL,
                City VARCHAR(80) NOT NULL,
                Uf CHAR(2) NOT NULL,
                Cep CHAR(8) NOT NULL,
                CONSTRAINT Supplier_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN Supplier.CompanyName IS 'Nome fantasia';
COMMENT ON COLUMN Supplier.TradingName IS 'Razão social';
COMMENT ON COLUMN Supplier.Cnpj IS 'CNPJ';
COMMENT ON COLUMN Supplier.PhoneNumber IS 'Telefone';
COMMENT ON COLUMN Supplier.EmailAddress IS 'Email';
COMMENT ON COLUMN Supplier.PublicArea IS 'Logradouro';
COMMENT ON COLUMN Supplier.District IS 'Bairro';
COMMENT ON COLUMN Supplier.City IS 'Cidade';
COMMENT ON COLUMN Supplier.Uf IS 'Unidade federativa';
COMMENT ON COLUMN Supplier.Cep IS 'CEP';


CREATE TABLE StockReceipt (
                Id BIGINT NOT NULL,
                CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL,
                ReceiptDate DATE NOT NULL,
                SupplierId INTEGER NOT NULL,
                CONSTRAINT StockReceipt_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN StockReceipt.CreatedAt IS 'Data de criação do registro. A data atual deve ser setada automaticamente';
COMMENT ON COLUMN StockReceipt.ReceiptDate IS 'Data de recebimento no estoque';


CREATE TABLE StockReceiptItem (
                Id BIGINT NOT NULL,
                StockReceiptId BIGINT NOT NULL,
                Quantity SMALLINT NOT NULL,
                CONSTRAINT StockReceiptItem_PK PRIMARY KEY (Id, StockReceiptId)
);
COMMENT ON COLUMN StockReceiptItem.Quantity IS 'Quantidade de itens recebidos';


CREATE TABLE Department (
                Id SMALLINT NOT NULL,
                Name VARCHAR(80) NOT NULL,
                PersonResponsible VARCHAR(80) NOT NULL,
                CONSTRAINT Department_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN Department.Name IS 'Nome do departamento';
COMMENT ON COLUMN Department.PersonResponsible IS 'Pessoa responsável';


CREATE TABLE StockIssue (
                Id BIGINT NOT NULL,
                CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL,
                IssueDate DATE NOT NULL,
                DepartmentId SMALLINT NOT NULL,
                CONSTRAINT StockIssue_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN StockIssue.CreatedAt IS 'Data de criação do registro. A data atual deve ser setada automaticamente';
COMMENT ON COLUMN StockIssue.IssueDate IS 'Data de saída no estoque';


CREATE TABLE StockIssueItem (
                Id BIGINT NOT NULL,
                StockIssueId BIGINT NOT NULL,
                Quantity SMALLINT NOT NULL,
                CONSTRAINT StockIssueItem_PK PRIMARY KEY (Id, StockIssueId)
);
COMMENT ON COLUMN StockIssueItem.Quantity IS 'Quantidade de itens saindo';


CREATE TABLE ItemGroup (
                Id SMALLINT NOT NULL,
                GroupType CHAR(7) NOT NULL,
                Name VARCHAR(80) NOT NULL,
                Observation VARCHAR(300),
                CONSTRAINT ItemGroup_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN ItemGroup.GroupType IS 'Tipo do grupo: Produto ou Serviço';
COMMENT ON COLUMN ItemGroup.Name IS 'Nome do grupo';
COMMENT ON COLUMN ItemGroup.Observation IS 'Observação';


CREATE TABLE Item (
                Id BIGINT NOT NULL,
                CreateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL,
                Description VARCHAR(120) NOT NULL,
                CostPrice DECIMAL(12,2) NOT NULL,
                SalePrice DECIMAL(12,2) NOT NULL,
                StockQuantity INTEGER NOT NULL,
                LastStockUpdate TIMESTAMP NOT NULL,
                ItemGroupId SMALLINT NOT NULL,
                CONSTRAINT Item_PK PRIMARY KEY (Id)
);
COMMENT ON COLUMN Item.CreateAt IS 'Data de criação do registro. A data atual deve ser setada automaticamente';
COMMENT ON COLUMN Item.Description IS 'Descrição';
COMMENT ON COLUMN Item.CostPrice IS 'Preço de custo';
COMMENT ON COLUMN Item.SalePrice IS 'Preço de venda';
COMMENT ON COLUMN Item.StockQuantity IS 'Quantidade no estoque';
COMMENT ON COLUMN Item.LastStockUpdate IS 'Última atualização do estoque';


ALTER TABLE StockReceipt ADD CONSTRAINT Supplier_StockReceipt_fk
FOREIGN KEY (SupplierId)
REFERENCES Supplier (Id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE StockReceiptItem ADD CONSTRAINT StockReceipt_StockReceipt_fk
FOREIGN KEY (StockReceiptId)
REFERENCES StockReceipt (Id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE StockIssue ADD CONSTRAINT Department_StockIssue_fk
FOREIGN KEY (DepartmentId)
REFERENCES Department (Id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE StockIssueItem ADD CONSTRAINT StockIssue_StockIssueItem_fk
FOREIGN KEY (StockIssueId)
REFERENCES StockIssue (Id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE Item ADD CONSTRAINT ItemGroup_Item_fk
FOREIGN KEY (ItemGroupId)
REFERENCES ItemGroup (Id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;
