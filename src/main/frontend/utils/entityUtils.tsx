import {useEffect, useState} from 'react';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/EntityX";
import {EntityService} from "Frontend/generated/endpoints";

export {HnomQngu as HnomQnguComponent};

const HnomQngu = ({entityId, markedId}: {entityId: number | undefined, markedId: number}): JSX.Element => {
    const [entity, setEntity] = useState<EntityX | undefined>();
    const [hnomString, setHnomString] = useState<string>('');
    const [qnguString, setQnguString] = useState<string>('');

    useEffect(() => {
        if (!entityId) return;

        const loadData = async () => {
            const fetchedEntity = await EntityService.findById(entityId) ?? undefined;
            const fetchedHnom = await EntityService.getHnomStringById(entityId) ?? '';
            const fetchedQngu = await EntityService.getQnguStringById(entityId) ?? '';

            setEntity(fetchedEntity);
            setHnomString(fetchedHnom);
            setQnguString(fetchedQngu);
        };

        loadData();
    }, [entityId]);

    const content = (
        <div
            style={{
                textAlign: 'center',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                color: entity?.id === markedId ? 'blue' : 'black',
            }}
        >
            <div>
                <p style={{fontSize: '0.8em', margin: '0px'}}>
                    {qnguString}
                </p>
            </div>
            <div style={{width: 'fit-content'}}>
                <p style={{fontSize: '1.5em', margin: '0px'}}>
                    {hnomString}
                </p>
            </div>
        </div>
    );

    return entity?.attested ? (
        <a
            href={'/entity/' + entity.id}
            style={{textDecoration: 'none', color: 'black'}}
            title={entity.explanationsString}
        >
            {content}
        </a>
    ) : (
        <div style={{color: 'grey'}}>
            {content}
        </div>
    );
};
