CREATE TABLE Recipe (

    id   BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    rating INTEGER DEFAULT NULL,
    prepareDuration INTERVAL DEFAULT NULL,
    link TEXT DEFAULT NULL
    -- PICTURE ????
);

CREATE TABLE Step (

    recipe_id BIGINT NOT NULL,
    description VARCHAR(255) NOT NULL,
    stepNumber INTEGER NOT NULL,

    CONSTRAINT fk_Step_Recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE
);

CREATE INDEX idx_recipe_id ON Step(recipe_id);

CREATE TABLE Ingredient (

    id BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    approved BOOLEAN NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Recipe_Ingredient(

    id BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    recipe_id NOT NULL,
    ingredient_id BIGINT NOT NULL,
    quantity DECIMAL NOT NULL,
    measure VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_Recipe_Ingredient_Recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    CONSTRAINT fk_Recipe_Ingredient_Ingredient_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE Category(

    id BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    approved BOOLEAN NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Recipe_Category(

    id BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    recipe_id NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_Recipe_Category_Recipe_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    CONSTRAINT fk_Recipe_Category_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE User (

    id BIGINT NOT NULL,
    createdDate DATETIME(6) NOT NULL,
    lastModifiedDate DATETIME(6) DEFAULT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    -- PICTURE ????

    PRIMARY KEY (id)
);