SET foreign_key_checks = 0;

DELETE FROM oauth2_registered_client;

INSERT INTO investor.oauth2_registered_client (id,client_id,client_id_issued_at,client_secret,client_secret_expires_at,client_name,client_authentication_methods,authorization_grant_types,redirect_uris,post_logout_redirect_uris,scopes,client_settings,token_settings) VALUES
	 ('1','smuaccount','2023-05-03 13:53:29','$2a$10$Vr0C31XJAjsJ5kdpszNQIu9Ay6PcWwsztm.AvyqL8SLLpUHXQGB3.',NULL,'1','client_secret_basic','refresh_token,authorization_code','http://localhost:3000/authorized,https://oidcdebugger.com/debug,https://oauth.pstmn.io/v1/callback',NULL,'accounts:write,users:read,accounts:read,users:write','{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}','{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000]}');

SET foreign_key_checks = 1;

ALTER TABLE oauth2_registered_client AUTO_INCREMENT = 1;