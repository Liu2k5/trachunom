import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useParams, useNavigate} from 'react-router';
import {useEffect, useState} from 'react';
import type EntityDetailDto from 'Frontend/generated/com/liu/trachunom/dto/EntityDetailDto';
import {getEntityDetail} from 'Frontend/generated/EntityDetailEndpoint';

import {
    DrawEntityYear,
    DrawEvolution,
    DrawMeaningEvolution,
    DrawPronunciationEvolution,
    AnalyseStructure,
    HnomQngu,
} from 'Frontend/utils/entityUtils';
import {SearchBar} from "Frontend/views/SearchBar";
import {Button} from "@vaadin/react-components";

export const config: ViewConfig = {
    title: 'Chi Tiết Thực Thể',
    route: 'entity/:id',
};


export default function EntityDetailView() {
    const {id} = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [entity, setEntity] = useState<EntityDetailDto | null | undefined>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // TODO: Fetch entity data from backend
        // Example: EntityService.getById(id)
        // Mock data for demonstration
        getEntityDetail(Number(id)).then((data) => {
            setEntity(data);
            setLoading(false);
        }).catch((error) => {
            console.error('Error fetching entity:', error);
            setLoading(false);
        });
    }, [id]);

    if (loading) {
        return (
            <div className="view-container" style={{display: 'flex', alignItems: 'center', justifyContent: 'center', height: '100%'}}>
                <div>Đang tải...</div>
            </div>
        );
    }

    if (!entity) {
        return (
            <div className="view-container" style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100%', gap: 'var(--lumo-space-l)'}}>
                <div style={{width: '100%', maxWidth: '800px'}}>
                    <SearchBar/>
                </div>
                <div style={{textAlign: 'center'}}>
                    <h2>Không tìm thấy thực thể</h2>
                    <Button theme="primary" onClick={() => navigate('/search')}>
                        Quay lại tìm kiếm
                    </Button>
                </div>
            </div>
        );
    }

    return (
        <div className="view-container">
            <SearchBar/>

            <div className="content-container">
                <Button onClick={() => navigate(-1)} style={{marginBottom: 'var(--lumo-space-m)'}}>
                    ← Quay lại
                </Button>

                {/* Main content */}
                <div style={{paddingTop: 'var(--lumo-space-l)'}}>
                    <div
                        style={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            borderBottom: '1px solid var(--lumo-contrast-10pct)',
                            paddingBottom: 'var(--lumo-space-l)',
                            marginBottom: 'var(--lumo-space-l)',
                            gap: 'var(--lumo-space-xl)'
                        }}
                    >
                        {/* Character display */}
                        <div
                            style={{
                                textAlign: 'center',
                                flex: '1',
                                minWidth: '200px',
                            }}
                        >
                            {!entity.compound ? (
                                    <>
                                        <div
                                            style={{
                                                fontSize: '80px',
                                                fontWeight: 'bold',
                                                color: 'var(--lumo-primary-text-color)',
                                                display: 'flex',
                                                justifyContent: 'center',
                                                alignItems: 'center',
                                                minHeight: '100px',
                                            }}
                                        >
                                            <HnomQngu entityId={entity.id} markedId={0}/>
                                        </div>
                                        {entity.structure?.character ? (
                                            <div>
                                                <p>Unicode: U+{(entity.structure?.character?.characterString?.codePointAt(0))?.toString(16).toUpperCase()}</p>
                                                <p>Bộ {entity.structure?.character?.radicalString}
                                                    + {entity.structure?.character?.additionalStrokeNumber} nét,
                                                    tổng {entity.structure?.character?.totalStrokeNumber}
                                                </p>
                                            </div>
                                        ) : (
                                                <div>
                                                    Kí tự này chưa được Unicode mã hóa
                                                </div>
                                            )
                                        }
                                        {/* Pronunciation */}
                                        {entity.pronunciation && (
                                            <section style={{marginTop: 'var(--lumo-space-m)'}}>
                                                <div style={{fontSize: '1.2em'}}>
                                                    <DrawPronunciationEvolution pronunciationId={entity.pronunciation.id}/>
                                                </div>
                                            </section>
                                        )}
                                    </>
                                )
                                :
                                <>
                                    <div
                                        style={{
                                            fontSize: '80px',
                                            fontWeight: 'bold',
                                            color: 'var(--lumo-primary-text-color)',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            alignItems: 'center',
                                            minHeight: '100px',
                                        }}
                                    >
                                        {entity.compositionComponents?.map((component, index) => (
                                            <div key={index}>
                                                {component ? (
                                                    <HnomQngu entityId={component.id} markedId={0}/>
                                                ) : (
                                                    <div style={{color: 'grey'}}>không có</div>
                                                )}
                                            </div>
                                        ))}
                                    </div>
                                    <div>
                                        <p>{entity.pronunciation?.pronunciationString}</p>
                                    </div>
                                </>
                            }
                        </div>

                        <div
                            style={{
                                flex: '2',
                                minWidth: '300px',
                                display: 'flex',
                                flexDirection: 'column',
                                gap: 'var(--lumo-space-l)'
                            }}
                        >
                            {/* Structure */}
                            {entity.structure && (
                                <section>
                                    <h2 className="section-title">Cấu tạo</h2>
                                    <div style={{width: '100%'}}>
                                        <AnalyseStructure structureId={entity.structure.id}/>
                                    </div>
                                </section>
                            )}
                            {/* Age */}
                            <section>
                                <h2 className="section-title">Niên đại</h2>
                                <div style={{fontSize: '1.1em'}}>
                                    <DrawEntityYear entityId={entity.id} />
                                </div>
                            </section>
                            {/* Meanings */}
                            {entity.meaning?.explanations && entity.meaning.explanations.length > 0 && (
                                <section>
                                    <h2 className="section-title">Ý nghĩa</h2>
                                    <DrawMeaningEvolution meaningId={entity.meaning.id}/>
                                </section>
                            )}
                            {/* Synonyms */}
                            {entity.synonyms && entity.synonyms.length > 0 && (
                                <section>
                                    <h2 className="section-title">Từ đồng nghĩa</h2>
                                    <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)', fontSize: '2em'}}>
                                        {entity.synonyms?.map(synonym =>
                                            synonym ? (
                                                <HnomQngu key={synonym.id} entityId={synonym.id} markedId={0}/>
                                            ) : null
                                        )}
                                    </div>
                                </section>
                            )}
                        </div>
                    </div>

                    {/* Evolution */}
                    {entity.evolutions && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Quá trình phát triển</h2>
                            <div>
                                <div
                                    style={{
                                        fontSize: '2em',
                                    }}
                                >
                                    <HnomQngu entityId={entity.id} markedId={0}/>
                                </div>
                                <p>{entity.explanationsString}</p>
                            </div>
                            <div>
                                <DrawEvolution entityId={entity.id} />
                            </div>
                        </section>
                    )}

                    {/* Variances */}
                    {entity.variants && entity.variants.length > 0 && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Biến thể</h2>
                            <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)', fontSize: '2em'}}>
                                {entity.variants.map(variant =>
                                    variant ? (
                                        <HnomQngu key={variant.id} entityId={variant.id} markedId={0}/>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Being semantic component */}
                    {entity.beingSemanticComponents && entity.beingSemanticComponents.length > 0 && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Làm hình bàng</h2>
                            <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)', fontSize: '2em'}}>
                                {entity.beingSemanticComponents?.map(fetchedEntity => (
                                    <HnomQngu key={fetchedEntity?.id} entityId={fetchedEntity?.id} markedId={0}/>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Being phonetic component */}
                    {entity.beingPhoneticComponents && entity.beingPhoneticComponents.length > 0 && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Làm thanh bàng</h2>
                            <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)', fontSize: '2em'}}>
                                {entity.beingPhoneticComponents?.map(fetchedEntity => (
                                    <HnomQngu key={fetchedEntity?.id} entityId={fetchedEntity?.id} markedId={0}/>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Having same semantic component */}
                    {entity.havingSameSemanticComponents && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Cùng hình bàng</h2>
                            {Object.entries(entity.havingSameSemanticComponents)?.map(([key, fetchedEntity]) => (
                                <div key={key} style={{display: 'flex', alignItems: 'baseline', gap: 'var(--lumo-space-m)', marginBottom: 'var(--lumo-space-s)'}}>
                                    <h3 style={{margin: '0', fontSize: '1.2em', color: 'var(--lumo-primary-text-color)'}}>{key}</h3>
                                    <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-s)', fontSize: '1.5em'}}>
                                        {fetchedEntity?.map(entity => (
                                            <HnomQngu key={entity?.id} entityId={entity?.id} markedId={0}/>
                                        ))}
                                    </div>
                                </div>
                            ))}
                        </section>
                    )}

                    {/* Having same phonetic component */}
                    {entity.havingSamePhoneticComponents && (
                        <section style={{marginBottom: 'var(--lumo-space-l)'}}>
                            <h2 className="section-title">Cùng thanh bàng</h2>
                            {Object.entries(entity.havingSamePhoneticComponents)?.map(([key, fetchedEntity]) => (
                                 <div key={key} style={{display: 'flex', alignItems: 'baseline', gap: 'var(--lumo-space-m)', marginBottom: 'var(--lumo-space-s)'}}>
                                    <h3 style={{margin: '0', fontSize: '1.2em', color: 'var(--lumo-primary-text-color)'}}>{key}</h3>
                                    <div style={{display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-s)', fontSize: '1.5em'}}>
                                        {fetchedEntity?.map(entity => (
                                            <HnomQngu key={entity?.id} entityId={entity?.id} markedId={0}/>
                                        ))}
                                    </div>
                                </div>
                            ))}
                        </section>
                    )}

                    {/* Examples */}
                    {entity.examples && entity.examples.length > 0 && (
                        <section>
                            <h2 className="section-title">Ví dụ</h2>
                            {entity?.examples.map((example, index) =>
                                <div key={index} style={{marginBottom: 'var(--lumo-space-m)'}}>
                                    <div style={{display: 'flex', flexWrap: 'wrap', alignItems: 'center', gap: 'var(--lumo-space-s)', fontSize: '1.8em'}}>
                                        {example?.exampleWords?.map((word, wordIndex) =>
                                            word?.entity ? <HnomQngu entityId={word.entity.id} markedId={entity.id ?? 0} key={wordIndex}/> : null
                                        )}
                                    </div>
                                    <p title={example?.sourceDescription ?? ''} style={{margin: '0', fontStyle: 'italic', color: 'var(--lumo-tertiary-text-color)', fontSize: '0.9em'}}>
                                        {example?.sourceName ?? ''}
                                    </p>
                                </div>
                            )}
                        </section>
                    )}
                </div>
            </div>
        </div>
    );
}
