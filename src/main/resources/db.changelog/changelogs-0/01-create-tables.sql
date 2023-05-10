CREATE TABLE languages (
    language_id varchar(255) NOT NULL,
    language_name varchar(255) NOT NULL,
    PRIMARY KEY (language_id)
);

CREATE TABLE seniority (
    seniority_id varchar(255) NOT NULL,
    seniority_name varchar(255) NOT NULL,
    PRIMARY KEY (seniority_id)
);

CREATE TABLE team (
    team_id varchar(255) NOT NULL,
    team_name varchar(255) NOT NULL,
    PRIMARY KEY (team_id)
);

CREATE TABLE project (
    project_id varchar(255) NOT NULL,
    project_name varchar(255) NOT NULL,
    description varchar(255),
    team_id varchar(255),
    price float,
    PRIMARY KEY (project_id)
);

CREATE TABLE users (
    user_id varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    age int, --???
    cv_id varchar(255),
    project_id varchar(255),
    PRIMARY KEY (user_id)
);

CREATE TABLE cv (
    cv_id varchar(255) NOT NULL,
    user_id varchar(255) NOT NULL,
    seniority_id varchar(255) NOT NULL,
    description varchar(255),
    PRIMARY KEY (cv_id)
);

CREATE TABLE positions (
    position_id varchar(255) NOT NULL,
    project_id varchar(255) NOT NULL,
    seniority_id varchar(255) NOT NULL,
    position_status varchar(255),
    user_id varchar(255),
    PRIMARY KEY (position_id)
);

-- tables for many-to-many relationships
CREATE TABLE language_position (
    language_id varchar(255) NOT NULL,
    position_id varchar(255) NOT NULL,
    PRIMARY KEY (language_id, position_id)
);

CREATE TABLE language_cv (
    language_id varchar(255) NOT NULL,
    cv_id varchar(255) NOT NULL,
    PRIMARY KEY (language_id, cv_id)
);

CREATE TABLE user_team (
    user_id varchar(255) NOT NULL,
    team_id varchar(255) NOT NULL,
    PRIMARY KEY (user_id, team_id)
);

-- defining foreign keys
ALTER TABLE project ADD CONSTRAINT FK_TEAM_PROJECT FOREIGN KEY (team_id) REFERENCES team(team_id);

ALTER TABLE users ADD CONSTRAINT FK_USER_CV FOREIGN KEY (cv_id) REFERENCES cv(cv_id);
ALTER TABLE users ADD CONSTRAINT FK_USER_PROJECT FOREIGN KEY (project_id) REFERENCES project(project_id);

ALTER TABLE cv ADD CONSTRAINT FK_CV_USER FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE cv ADD CONSTRAINT FK_CV_SENIORITY FOREIGN KEY (seniority_id) REFERENCES seniority(seniority_id);

ALTER TABLE positions ADD CONSTRAINT FK_POSITION_PROJECT FOREIGN KEY (project_id) REFERENCES project(project_id);
ALTER TABLE positions ADD CONSTRAINT FK_POSITION_SENIORITY FOREIGN KEY (seniority_id) REFERENCES seniority(seniority_id);
ALTER TABLE positions ADD CONSTRAINT FK_POSITION_USER FOREIGN KEY (user_id) REFERENCES users(user_id);

-- defining foreign keys for many-to-many relationships
ALTER TABLE language_position ADD CONSTRAINT FK_LANG_POS_LANGUAGE FOREIGN KEY (language_id) REFERENCES languages(language_id);
ALTER TABLE language_position ADD CONSTRAINT FK_LANG_POS_POSITION FOREIGN KEY (position_id) REFERENCES positions(position_id);

ALTER TABLE language_cv ADD CONSTRAINT FK_LANG_CV_LANGUAGE FOREIGN KEY (language_id) REFERENCES languages(language_id);
ALTER TABLE language_cv ADD CONSTRAINT FK_LANG_CV_CV FOREIGN KEY (cv_id) REFERENCES cv(cv_id);

ALTER TABLE user_team ADD CONSTRAINT FK_USER_TEAM_USER FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE user_team ADD CONSTRAINT FK_USER_TEAM_TEAM FOREIGN KEY (team_id) REFERENCES team(team_id);