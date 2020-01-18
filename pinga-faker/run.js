const csvReader = require('./csv/csvReader');
const csvParser = require('./csv/csvParser');
const pinga = require('./pingFaker/pinga');

const run = async (config) => {
  console.log('CONFIG => ', config);
  console.log('Loading test data...');
  const csvData = await csvReader.load('./data/latlng.csv');

  console.log('Converting test data to location objects...');
  const locations = csvParser.parseCsv(csvData, (row) => ({
    latitude: row[0],
    longitude: row[1]
  }));

  console.log(`Successfully loaded ${locations.length} locations`);
  console.log('Start pinga...');
  pinga.start(config, locations);
};

const config = {
  accountId: '00000003-0000-1000-8000-00805f9b34fb',
  transmitterId: '398d4277-300e-4c8f-b10b-ee9c98bdfd66',
  baseUrl: 'http://localhost:8080'
};

run(config);
