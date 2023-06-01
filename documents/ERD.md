```mermaid
%%{`  More info on mermaid notation see: https://mermaid.js.org/syntax/entityRelationshipDiagram.html.  `}%%
erDiagram
    user ||--o{ recipe : Places
    kitchen_region ||--o{ recipe : Places
    kitchen_type ||--o{ recipe : Places
    image ||--o{ recipe : Places
    recipe ||--o{ ingredient : Places
    recipe ||--o{ preparation : Places
    rating ||--o{ recipe : Places
    recipe ||--o{ kitchen_categories_link : Places
    kitchen_category ||--o{ kitchen_categories_link : Places
    article ||--o{ ingredient :  Places
    comment ||--o{ user : Places
    comment ||--o{ recipe : Places
    rating ||--o{ user : Places
    rating ||--o{ recipe : Places
    favorite ||--o{ user : Places
    favorite ||--o{ recipe : Places
    user ||--o{ calendar_item : Places
    
    
    user {
        int    id        PK
        string name
        string email     UK
        string encrypted_password
        string otp
    }    
    recipe {
        int    id                PK
        string description
        int     persons
        string  title
        datetime time_added
        string name              
        string slug             UK  %%{` a slang is a url friendly concaternation of the name `}%%
        int    user_id           FK
        int    kitchen_region_id       FK
        int    kitchen_type_id      FK
        int    image_id         FK     
    }
    ingredient {
        int id PK
        int amount
        int article_id FK
        int recipe_id FK
    }
    article {
        int id PK
        int calories
        string description
        string name
        int price
        string unit
    }
    comment {
        int id PK
        string comment_text
        int recipe_id FK
        int user_id FK
    }
    favorite {
        int id PK
        int recipe_id FK
        int user_id FK
    }
    image {
        int id PK
        mediumblob imagedata
        string name
        string type
    }
    kitchen_categories_link{
        int id PK
        int recipe_id FK
        int kitchen_category_id FK
    }
    kitchen_category {
        int id PK
        string name
    }
    kitchen_region {
        int id PK
        string name
    }
    kitchen_type {
        int id PK
        string name
    }
    preparation {
        int id PK
        string instructions
        int step
        int recipe_id FK
    }
    rating {
        int id PK
        int rating
        int recipe_id FK
        int user_id FK
    }
    calendar_item {
        int id PK
        date date
        int user_id FK
        String discription
    }
%%{` Fill in the rest of the tables `}%%  
```
