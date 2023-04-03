FROM mongo

COPY schools.json /schools.json
COPY users.json /users.json

CMD mongoimport --host database --db school-app --collection schools --type json --file /schools.json --jsonArray && mongoimport --host database --db school-app --collection users --type json --file /users.json --jsonArray