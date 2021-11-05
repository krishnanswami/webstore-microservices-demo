echo "Getting databases..."
DBS=$(curl -s --request GET \
  --url "https://api.astra.datastax.com/v2/databases?include=nonterminated&provider=all&limit=25" \
  --header "authorization: Bearer AstraCS:dhdIZyEWUmvHsPXgUUZtQZnS:88fa2ad6e59d9ff7ed18617519834411d289b9c83ac11fa8a5358a852f6c5ae1" \
  --header 'content-type: application/json')

# TODO: Allow the user to select the DB
NUM_DBS=$(echo "${DBS}" | jq -c 'length')
FIRST_DB_ID=$(echo "${DBS}" | jq -c '.[0].id')
FIRST_DB_REGION=$(echo "${DBS}" | jq -c '.[0].info.region')
FIRST_DB_USER=$(echo "${DBS}" | jq -c '.[0].info.user')

# TODO: Allow the user to select a keyspace
FIRST_DB_KEYSPACE=$(echo "${DBS}" | jq -c '.[0].info.keyspaces[0]')
FIRST_DB_SECURE_BUNDLE_URL=$(echo "${DBS}" | jq -c '.[0].info.datacenters[0].secureBundleUrl')

export ASTRA_SECURE_BUNDLE_URL=${FIRST_DB_SECURE_BUNDLE_URL}
gp env ASTRA_SECURE_BUNDLE_URL=${FIRST_DB_SECURE_BUNDLE_URL} &>/dev/null

# Download the secure connect bundle
curl -s -L $(echo $FIRST_DB_SECURE_BUNDLE_URL | sed "s/\"//g") -o astra-creds.zip

export ASTRA_DB_BUNDLE="astra-creds.zip"
gp env ASTRA_DB_BUNDLE="astra-creds.zip" &>/dev/null

export ASTRA_DB_USERNAME=$(echo ${FIRST_DB_USER} | sed "s/\"//g")
gp env ASTRA_DB_USERNAME=$(echo ${FIRST_DB_USER} | sed "s/\"//g") &>/dev/null

export ASTRA_DB_KEYSPACE=$(echo ${FIRST_DB_KEYSPACE} | sed "s/\"//g")
gp env ASTRA_DB_KEYSPACE=$(echo ${FIRST_DB_KEYSPACE} | sed "s/\"//g") &>/dev/null

export ASTRA_DB_ID=$(echo ${FIRST_DB_ID} | sed "s/\"//g")
gp env ASTRA_DB_ID=$(echo ${FIRST_DB_ID} | sed "s/\"//g") &>/dev/null

export ASTRA_DB_REGION=$(echo ${FIRST_DB_REGION} | sed "s/\"//g")
gp env ASTRA_DB_REGION=$(echo ${FIRST_DB_REGION} | sed "s/\"//g") &>/dev/null


echo "What is your Astra DB password? 🔒"
read -s ASTRA_DB_PASSWORD
export ASTRA_DB_PASSWORD=${ASTRA_DB_PASSWORD}
gp env ASTRA_DB_PASSWORD=${ASTRA_DB_PASSWORD} &>/dev/null

echo "You're all set 👌"