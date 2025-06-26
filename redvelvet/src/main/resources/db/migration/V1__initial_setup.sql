CREATE SEQUENCE red_velvet_sequence_id START WITH 1 INCREMENT BY 50;

CREATE TABLE recipe (

    id                  BIGINT          NOT NULL,
    created_date        timestamp(6)    NOT NULL,
    last_modified_date  timestamp(6)    DEFAULT NULL,
    name                VARCHAR(255)    NOT NULL,
    rating              INTEGER         DEFAULT NULL,
    prepare_duration    BIGINT          DEFAULT NULL,
    link                TEXT            DEFAULT NULL,
    -- PICTURE ????

    PRIMARY KEY (id)
);

CREATE TABLE recipe_procedure (

    recipe_id   BIGINT       NOT NULL,
    description VARCHAR(255) NOT NULL,
    step_number INTEGER      NOT NULL,

    CONSTRAINT fk_recipe_procedure_recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE
);

CREATE INDEX idx_recipe_id ON recipe_procedure(recipe_id);

CREATE TABLE ingredient (

    id                  BIGINT       NOT NULL,
    created_date        timestamp(6) NOT NULL,
    last_modified_date  timestamp(6) DEFAULT NULL,
    name                VARCHAR(255) NOT NULL,
    approved            BOOLEAN NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredient(

    id                  BIGINT          NOT NULL,
    created_date        timestamp(6)    NOT NULL,
    last_modified_date  timestamp(6)    DEFAULT NULL,
    recipe_id           BIGINT          NOT NULL,
    ingredient_id       BIGINT          NOT NULL,
    quantity            DECIMAL         NOT NULL,
    measure             VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_Recipe_Ingredient_Recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    CONSTRAINT fk_Recipe_Ingredient_Ingredient_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE category(

    id                  BIGINT          NOT NULL,
    created_date        timestamp(6)    NOT NULL,
    last_modified_date  timestamp(6)    DEFAULT NULL,
    name                VARCHAR(255)    NOT NULL,
    approved            BOOLEAN         NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE recipe_category(

    id                  BIGINT NOT NULL,
    created_date        timestamp(6) NOT NULL,
    last_modified_date  timestamp(6) DEFAULT NULL,
    recipe_id           BIGINT NOT NULL,
    category_id         BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_Recipe_Category_Recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    CONSTRAINT fk_Recipe_Category_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE "user" (

    id                  BIGINT          NOT NULL,
    created_date        timestamp(6)    NOT NULL,
    last_modified_date  timestamp(6)    DEFAULT NULL,
    username            VARCHAR(255)    NOT NULL UNIQUE,
    password            VARCHAR(64)     NOT NULL,
    role                VARCHAR(255)    NOT NULL,
    enabled             BOOLEAN         NOT NULL,
    -- PICTURE ????

    PRIMARY KEY (id)
);