// Generated using typescript-generator version 1.24.318 on 2017-05-27 21:16:58.

declare namespace Domain {

    interface AbstractTask extends Entity {
        description: string;
        taskGroupId: string;
    }

    interface Entity {
        id: string;
    }

    interface KeyValue {
        key: string;
        value: string;
    }

    interface QuartzJob extends Job {
    }

    interface ScheduledTask extends AbstractTask {
        taskId: string;
        cron: string;
    }

    interface Task extends AbstractTask {
        headers: KeyValue[];
        protocol: string;
        domain: string;
        resourcePath: string;
        port: string;
        method: Method;
        queryParams: KeyValue[];
        body: string;
    }

    interface TaskGroup extends AbstractTask {
    }

    interface TaskResult extends Entity {
        taskId: string;
        taskResult: string;
    }

    interface Job {
    }

    type Method = "GET" | "POST" | "DELETE" | "PUT";

}
