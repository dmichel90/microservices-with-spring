DO
$do$
    BEGIN
        IF
            NOT EXISTS(SELECT
                       FROM pg_roles
                       WHERE rolname = 'restaurant_finder_user') THEN
            CREATE
                USER "restaurant_finder_user" WITH PASSWORD 'secret' CREATEDB;
        END IF;
    END
$do$;
CREATE
    DATABASE "restaurant_db" WITH
    OWNER = "restaurant_finder_user"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
CREATE
    DATABASE "food_db" WITH
    OWNER = "restaurant_finder_user"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

