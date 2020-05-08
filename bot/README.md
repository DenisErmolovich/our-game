# Our Game Buttons Bot
Bot witch help us play together

## DB
* Create volumes:  
  `docker volume create bot-db-data`  
  `docker volume create bot-db-config`
* Run DB container `docker run -p 27021:27017 -v bot-db-data:/data/db -v bot-db-config:/data/configdb -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=xpaIWgQVgJlF -e MONGO_INITDB_DATABASE=bot -d --name bot-db mongo:3`

