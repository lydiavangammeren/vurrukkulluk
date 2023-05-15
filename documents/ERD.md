```mermaid
%%{`  More info on mermaid notation see: https://mermaid.js.org/syntax/entityRelationshipDiagram.html.  `}%%
erDiagram
    users ||--o{ recipes : Places
    users {
        int    id        PK
        string name
        string email     UK
        string encrypted_password
        string otp
    }    
    recipes {
        int    id                PK
        int    user_id           FK
        string name              
        string slang             UK  %%{` a slang is a url friendly concaternation of the name `}%%
        %%{` Fill in the rest of the properties `}%%        
    }
%%{` Fill in the rest of the tables `}%%  
```
